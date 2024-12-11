package com.ochobits.optica.athentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDto(
        Long id,
        @JsonProperty("username")
        String userName,
        String password
) {
}
