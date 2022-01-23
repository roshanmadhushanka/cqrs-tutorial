package com.xventure.user.cmd.api.dto;

import lombok.Getter;

@Getter
public class RegisterUserResponse extends BaseResponse {

    private final String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
