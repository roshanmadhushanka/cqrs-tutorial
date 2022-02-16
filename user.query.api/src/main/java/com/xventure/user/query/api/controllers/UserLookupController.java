package com.xventure.user.query.api.controllers;

import com.xventure.user.query.api.dto.UserLookupResponse;
import com.xventure.user.query.api.queries.FindAllUsersQuery;
import com.xventure.user.query.api.queries.FindUserByIdQuery;
import com.xventure.user.query.api.queries.SearchUsersQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    public UserLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "")
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        try {
            UserLookupResponse userLookupResponse = queryGateway.query(new FindAllUsersQuery(),
                    ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (userLookupResponse == null || userLookupResponse.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userLookupResponse, HttpStatus.OK);
        } catch (Exception e) {
            String businessErrorMessage = "Failed to complete get all users request";
            log.error(businessErrorMessage, e);
            return new ResponseEntity<>(new UserLookupResponse(businessErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id) {
        try {
            UserLookupResponse userLookupResponse = queryGateway.query(new FindUserByIdQuery(id),
                    ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (userLookupResponse == null || userLookupResponse.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userLookupResponse, HttpStatus.OK);
        } catch (Exception e) {
            String businessErrorMessage = "Failed to complete get user by id request";
            log.error(businessErrorMessage, e);
            return new ResponseEntity<>(new UserLookupResponse(businessErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/filter/{filter}")
    public ResponseEntity<UserLookupResponse> getUserByFilter(@PathVariable(value = "filter") String filter) {
        try {
            UserLookupResponse userLookupResponse = queryGateway.query(new SearchUsersQuery(filter),
                    ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (userLookupResponse == null || userLookupResponse.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userLookupResponse, HttpStatus.OK);
        } catch (Exception e) {
            String businessErrorMessage = "Failed to complete get user search request";
            log.error(businessErrorMessage, e);
            return new ResponseEntity<>(new UserLookupResponse(businessErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
