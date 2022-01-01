package com.xventure.user.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRemovedEvent {

    private String id;
}
