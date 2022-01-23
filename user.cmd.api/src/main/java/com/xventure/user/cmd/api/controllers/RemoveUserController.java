package com.xventure.user.cmd.api.controllers;

import com.xventure.user.cmd.api.commands.RemoveUserCommand;
import com.xventure.user.cmd.api.dto.RemoveUserResponse;
import com.xventure.user.cmd.api.dto.UpdateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/removeUser")
public class RemoveUserController {

    private final CommandGateway commandGateway;

    public RemoveUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RemoveUserResponse> removeUser(@PathVariable("id") String id) {
        try {
            commandGateway.send(new RemoveUserCommand(id));
            return new ResponseEntity<>(new RemoveUserResponse("User was successfully removed"), HttpStatus.OK);
        } catch (Exception e) {
            String businessErrorMessage = "Error occurred while processing remove user request for id - " + id;
            log.error(businessErrorMessage, e);
            return new ResponseEntity<>(new RemoveUserResponse(businessErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);        }
    }

}
