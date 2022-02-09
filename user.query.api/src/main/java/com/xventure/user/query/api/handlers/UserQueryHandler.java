package com.xventure.user.query.api.handlers;

import com.xventure.user.query.api.dto.UserLookupResponse;
import com.xventure.user.query.api.queries.FindAllUsersQuery;
import com.xventure.user.query.api.queries.FindUserByIdQuery;
import com.xventure.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {

    UserLookupResponse getUserById(FindUserByIdQuery query);

    UserLookupResponse searchUsers(SearchUsersQuery query);

    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
