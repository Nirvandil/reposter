package cf.nirvandil.reposter;

import cf.nirvandil.reposter.model.TgConfiguration;
import cf.nirvandil.reposter.model.VkConfiguration;
import cf.nirvandil.reposter.service.bots.VkBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

@SpringBootApplication
@EnableConfigurationProperties({VkConfiguration.class, TgConfiguration.class})
public class ReposterApplication implements CommandLineRunner {
    private final LongPollingBot tgBot;
    private final VkBot vkBot;

    @Autowired
    public ReposterApplication(LongPollingBot tgBot, VkBot vkBot) {
        this.tgBot = tgBot;
        this.vkBot = vkBot;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(ReposterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi api = new TelegramBotsApi();
        vkBot.startListen();
        api.registerBot(tgBot);
    }
}

