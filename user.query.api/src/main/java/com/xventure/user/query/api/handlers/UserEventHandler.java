package com.xventure.user.query.api.handlers;

import com.xventure.user.core.events.UserRegisteredEvent;
import com.xventure.user.core.events.UserRemovedEvent;
import com.xventure.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {

    void on(UserRegisteredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserRemovedEvent event);
}
