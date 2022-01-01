package com.xventure.user.cmd.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {

    private final BCryptPasswordEncoder encoder;

    public  PasswordEncoderImpl() {
        encoder = new BCryptPasswordEncoder(12);
    }

    @Override
    public String hashPassword(String password) {
        return encoder.encode(password);
    }
}
