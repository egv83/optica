package com.ochobits.optica.athentication.security.interfaces;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String requestPassword, String bddPassword);
}
