package com.ochobits.optica.athentication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {

    private Long id;
    private String userName;
    private String password;

}
