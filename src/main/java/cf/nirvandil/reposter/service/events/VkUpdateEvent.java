package cf.nirvandil.reposter.service.events;

import com.google.gson.JsonObject;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * Created on 17.12.18 at 13:59
 *
 * @author : Vladimir Suharev <vladimir.suharev@gost-group.com>
 */
public class VkUpdateEvent extends ApplicationEvent {
    @Getter
    private final List<JsonObject> updates;

    public VkUpdateEvent(List<JsonObject> updates, Object source) {
        super(source);
        this.updates = updates;
    }
}
