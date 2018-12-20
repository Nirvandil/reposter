package cf.nirvandil.reposter.service.bots.impl;

import cf.nirvandil.reposter.model.TgConfiguration;
import cf.nirvandil.reposter.service.bots.TgBot;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Created on 16.12.18 at 17:23
 *
 * @author : Vladimir Suharev <vladimir.suharev@gost-group.com>
 */
@Component
@RequiredArgsConstructor
public class LongPollingTgBot extends TelegramLongPollingBot implements TgBot {
    private final TgConfiguration configuration;

    @Override
    @SneakyThrows
    public void sendMessage(String text) {
        sendApiMethod(new SendMessage(configuration.getChannelId(), text));
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Ничего он не делает при обновлениях, просто отправляет сообщения когда попросят.
    }

    @Override
    public String getBotUsername() {
        return configuration.getName();
    }

    @Override
    public String getBotToken() {
        return configuration.getToken();
    }
}
