package cf.nirvandil.reposter.service;

import cf.nirvandil.reposter.service.bots.TgBot;
import cf.nirvandil.reposter.service.events.VkUpdateEvent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created on 17.12.18 at 14:07
 *
 * @author : Vladimir Suharev <vladimir.suharev@gost-group.com>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultBroker {
    private final TgBot tgBot;

    @EventListener(classes = VkUpdateEvent.class)
    public void processVkUpdates(VkUpdateEvent event) {
        tgBot.sendMessage(event.getUpdates().stream()
                .filter(this::isWallPost)
                .map(this::extractText)
                .collect(Collectors.joining(",")));
    }

    private String extractText(JsonObject jsonObject) {
        JsonElement object = jsonObject.get("object");
        String result;
        if (object instanceof JsonObject) {
            result = ((JsonObject) object).get("text").getAsString();
        } else
            result = object.toString();
        return result + "\n" + buildLink(jsonObject);
    }

    private boolean isWallPost(JsonObject jsonObject) {
        return jsonObject.get("type").getAsString().equals("wall_post_new");
    }

    private String buildLink(JsonObject object) {
        String groupId = object.get("group_id").getAsString();
        String id = ((JsonObject) object.get("object")).get("id").getAsString();
        return "https://vk.com/wall-".concat(groupId).concat("_").concat(id);
    }
}
