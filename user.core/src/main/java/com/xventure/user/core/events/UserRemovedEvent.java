package com.xventure.user.core.events;

import lombok.Builder;
import lombok.Data;
import org.axonframework.serialization.Revision;

@Data
@Builder
@Revision("1.0")
public class UserRemovedEvent {

    private String id;
}
