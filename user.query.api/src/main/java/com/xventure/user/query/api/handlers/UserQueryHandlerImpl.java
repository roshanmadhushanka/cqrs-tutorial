package com.xventure.user.query.api.handlers;

import com.xventure.user.query.api.dto.UserLookupResponse;
import com.xventure.user.query.api.queries.FindAllUsersQuery;
import com.xventure.user.query.api.queries.FindUserByIdQuery;
import com.xventure.user.query.api.queries.SearchUsersQuery;
import com.xventure.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    public UserQueryHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        return new UserLookupResponse(Collections.singletonList(
                Optional.of(userRepository.findById(query.getId())).map(Optional::get).orElse(null)));
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        return new UserLookupResponse(userRepository.findByFilterRegex(query.getFilter()));
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        return new UserLookupResponse(userRepository.findAll());
    }
}
