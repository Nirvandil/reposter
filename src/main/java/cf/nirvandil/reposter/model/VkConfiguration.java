package cf.nirvandil.reposter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 15.12.18 at 23:38
 *
 * @author : Vladimir Suharev <vladimir.suharev@gost-group.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "reposter.vk")
public class VkConfiguration {
    /**
     * Идентификатор группы вк.
     */
    private Integer groupId;
    /**
     * Ключ доступа к API группы (?act=tokens).
     */
    private String key;
    /**
     * Длительность long-polling подключения (в секундах).
     */
    private Integer timeout;
}
