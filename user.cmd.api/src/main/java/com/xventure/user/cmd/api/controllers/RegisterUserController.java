package com.xventure.user.cmd.api.controllers;

import com.xventure.user.cmd.api.commands.RegisterUserCommand;
import com.xventure.user.cmd.api.dto.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/registerUser")
public class RegisterUserController {

    private final CommandGateway commandGateway;

    public RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RegisterUserResponse(id,"User successfully created"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            String businessErrorMessage = "Error occurred while processing register user request for id - " + id;
            log.error(businessErrorMessage, e);
            return new ResponseEntity<>(new RegisterUserResponse(id, businessErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
