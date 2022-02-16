package com.xventure.user.query.api.dto;

import com.xventure.user.core.dto.BaseResponse;
import com.xventure.user.core.models.User;

import java.util.List;

public class UserLookupResponse extends BaseResponse {

    private List<User> users;

    public UserLookupResponse(String message, List<User> users) {
        super(message);
        this.users = users;
    }

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookupResponse(String message) {
        super(message);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
