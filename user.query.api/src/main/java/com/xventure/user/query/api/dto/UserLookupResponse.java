package com.xventure.user.query.api.dto;

import com.xventure.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserLookupResponse {

    private List<User> users;
}
