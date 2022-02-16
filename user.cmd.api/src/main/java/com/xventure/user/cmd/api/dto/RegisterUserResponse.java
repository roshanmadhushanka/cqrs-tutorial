package com.xventure.user.cmd.api.dto;

import com.xventure.user.core.dto.BaseResponse;
import lombok.Getter;

@Getter
public class RegisterUserResponse extends BaseResponse {

    private final String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
