package com.xventure.user.cmd.api.aggregates;

import com.xventure.user.cmd.api.commands.RegisterUserCommand;
import com.xventure.user.cmd.api.commands.RemoveUserCommand;
import com.xventure.user.cmd.api.commands.UpdateUserCommand;
import com.xventure.user.cmd.api.security.PasswordEncoder;
import com.xventure.user.cmd.api.security.PasswordEncoderImpl;
import com.xventure.user.core.events.UserRegisteredEvent;
import com.xventure.user.core.events.UserRemovedEvent;
import com.xventure.user.core.events.UserUpdatedEvent;
import com.xventure.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String id;
    private User user;
    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        this.passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        User newUser = command.getUser();
        newUser.setId(command.getId());
        String password = newUser.getAccount().getPassword();
        this.passwordEncoder = new PasswordEncoderImpl();
        newUser.getAccount().setPassword(passwordEncoder.hashPassword(password));

        AggregateLifecycle.apply(UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build());
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        User updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        String password = updatedUser.getAccount().getPassword();
        updatedUser.getAccount().setPassword(passwordEncoder.hashPassword(password));

        AggregateLifecycle.apply(UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build());
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        AggregateLifecycle.apply(UserRemovedEvent.builder()
                .id(command.getId())
                .build());
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
