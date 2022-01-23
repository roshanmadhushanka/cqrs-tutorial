package com.xventure.user.cmd.api.controllers;

import com.xventure.user.cmd.api.commands.UpdateUserCommand;
import com.xventure.user.cmd.api.dto.UpdateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/updateUser")
public class UpdateUserController {

    private final CommandGateway commandGateway;

    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable(value = "id") String id,
                                                         @Valid @RequestBody UpdateUserCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity<>(new UpdateUserResponse("User successfully updated"), HttpStatus.OK);
        } catch (Exception e) {
            String businessErrorMessage = "Error occurred while processing update user request for id - " + id;
            log.error(businessErrorMessage, e);
            return new ResponseEntity<>(new UpdateUserResponse(businessErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
