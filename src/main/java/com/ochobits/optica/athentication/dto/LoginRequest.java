package com.ochobits.optica.athentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest (
        @JsonProperty("username")
        String userName,
        String password
){
}
