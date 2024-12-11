package com.ochobits.optica.athentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record SignUpRequest(

        @JsonProperty("username")
        String userName,

        @Size(min = 8, message = "La clave no puede ser menor de 8 caracteres")
        String password
) {
}
