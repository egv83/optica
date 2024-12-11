package com.ochobits.optica.athentication.security.impl;

import com.ochobits.optica.athentication.security.interfaces.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoderImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String requestPassword, String bddPassword) {
        return passwordEncoder.matches(requestPassword,bddPassword);
    }
}
