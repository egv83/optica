package com.ochobits.optica.athentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        @JsonProperty("username")
        String userName,
        String token,
        String message
) {
}
