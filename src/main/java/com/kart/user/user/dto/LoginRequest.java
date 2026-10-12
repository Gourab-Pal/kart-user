package com.kart.user.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "Email can not be empty")
        @Email
        String email,

        @NotBlank(message = "Password can not be empty ")
        String password
) {
}
