package cf.nirvandil.reposter.config;

import cf.nirvandil.reposter.model.VkConfiguration;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 16.12.18 at 18:05
 *
 * @author : Vladimir Suharev <vladimir.suharev@gost-group.com>
 */
@Configuration
@RequiredArgsConstructor
public class AppConfiguration {
    private final VkConfiguration vkConfiguration;

    @Bean
    public VkApiClient vkApiClient() {
        HttpTransportClient transportClient = HttpTransportClient.getInstance();
        return new VkApiClient(transportClient);
    }

    @Bean
    public GroupActor groupActor() {
        return new GroupActor(vkConfiguration.getGroupId(), vkConfiguration.getKey());
    }
}
