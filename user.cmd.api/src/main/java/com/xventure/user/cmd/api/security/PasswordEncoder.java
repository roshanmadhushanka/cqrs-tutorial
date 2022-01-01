package com.xventure.user.cmd.api.security;

public interface PasswordEncoder {

    String hashPassword(String password);
}
