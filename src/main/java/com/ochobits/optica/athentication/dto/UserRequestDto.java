package com.ochobits.optica.athentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

public record UserRequestDto(
        @JsonProperty("username")
       @Valid String userName,
       @Valid String password
) {
}
