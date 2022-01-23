package com.xventure.user.core.events;

import com.xventure.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.serialization.Revision;

@Data
@Builder
@Revision("1.0")
public class UserUpdatedEvent {

    private String id;
    private User user;
}
