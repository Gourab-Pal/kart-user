package com.kart.user.user.controller;

import com.kart.user.user.dto.UserRegistrationRequest;
import com.kart.user.user.dto.UserResponse;
import com.kart.user.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(
            @Valid
            @RequestBody UserRegistrationRequest userRegistrationRequest
    ) {
        return userService.register(userRegistrationRequest);
    }
}
