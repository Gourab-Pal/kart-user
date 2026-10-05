package com.kart.user.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(

        @NotBlank (message = "Email can not be empty")
        @Email
        @Size(max = 255)
        String email,

        @NotBlank(message = "Password can not be empty")
        @Size(min = 8)
        String password,

        @NotBlank(message = "Full name can not be empty")
        @Size(max = 100)
        String fullName
) {
}
