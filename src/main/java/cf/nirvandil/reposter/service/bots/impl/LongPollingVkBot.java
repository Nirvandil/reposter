package cf.nirvandil.reposter.service.bots.impl;

import cf.nirvandil.reposter.model.VkConfiguration;
import cf.nirvandil.reposter.service.bots.VkBot;
import cf.nirvandil.reposter.service.events.VkUpdateEvent;
import com.google.gson.JsonObject;
import com.vk.api.sdk.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.LongPollServerKeyExpiredException;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 16.12.18 at 17:56
 *
 * @author : Vladimir Suharev <vladimir.suharev@gost-group.com>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LongPollingVkBot implements VkBot {
    private final ApplicationEventPublisher eventPublisher;
    private final VkApiClient apiClient;
    private final GroupActor groupActor;
    private final VkConfiguration configuration;
    private String server;
    private String key;
    private Integer currentTs;

    @Override
    @SneakyThrows
    public void startListen() {
        fetchServerAndKey();
        waitForUpdates();
    }

    /**
     * Ожидаем обновлений.
     */
    @SneakyThrows
    private void waitForUpdates() {
        while (true) {
            try {
                GetLongPollEventsResponse longPollResponse = apiClient.longPoll()
                        .getEvents(server, key, currentTs)
                        .waitTime(configuration.getTimeout())
                        .execute();
                currentTs = longPollResponse.getTs();
                List<JsonObject> updates = longPollResponse.getUpdates();
                if (!updates.isEmpty()) {
                    log.info("Received long polling updates {}", updates);
                    eventPublisher.publishEvent(new VkUpdateEvent(updates, this));
                } else {
                    log.debug("There was no updates in last {} seconds.", configuration.getTimeout());
                }
            } catch (LongPollServerKeyExpiredException expired) {
                log.warn("Key expired, re-fetching.");
                fetchServerAndKey();
            }
        }
    }

    /**
     * Получает обновлённый ключ и адрес сервера для long-polling.
     */
    @SneakyThrows
    private void fetchServerAndKey() {
        GetLongPollServerResponse response = apiClient.groups()
                .getLongPollServer(groupActor)
                .execute();
        log.info("Received getLongPollServer response {}", response);
        this.server = response.getServer();
        this.key = response.getKey();
        this.currentTs = response.getTs();
    }
}
