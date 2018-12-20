package cf.nirvandil.reposter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 15.12.18 at 23:40
 *
 * @author : Vladimir Suharev <vladimir.suharev@gost-group.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "reposter.tg")
public class TgConfiguration {
    /**
     * Токен телеграм-бота.
     */
    private String token;
    /**
     * Имя телеграм-бота.
     */
    private String name;
    /**
     * Идентификатор канала.
     */
    private String channelId;
}
