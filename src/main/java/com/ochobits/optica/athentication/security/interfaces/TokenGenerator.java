package com.ochobits.optica.athentication.security.interfaces;

import com.ochobits.optica.athentication.models.User;
import org.springframework.security.core.Authentication;

public interface TokenGenerator {

    String generate(User user);

}
