package com.xventure.user.query.api.handlers;

import com.xventure.user.core.models.User;
import com.xventure.user.query.api.dto.UserLookupResponse;
import com.xventure.user.query.api.queries.FindAllUsersQuery;
import com.xventure.user.query.api.queries.FindUserByIdQuery;
import com.xventure.user.query.api.queries.SearchUsersQuery;
import com.xventure.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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
        List<User> users = userRepository.findByFilterRegex(query.getFilter());
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        List<User> users = userRepository.findAll();
        return new UserLookupResponse(users);
    }
}
