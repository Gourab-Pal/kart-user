package com.kart.user.user.service;

import com.kart.user.common.dto.ExceptionResponse;
import com.kart.user.user.dto.UserRegistrationRequest;
import com.kart.user.user.dto.UserResponse;
import com.kart.user.user.entity.UserEntity;
import com.kart.user.user.exception.EmailAlreadyExistsException;
import com.kart.user.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(UserRegistrationRequest  userRegistrationRequest) {
        String normalizedEmail = userRegistrationRequest.email().trim().toLowerCase(Locale.ROOT);

        // user existence check
        if(userRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new EmailAlreadyExistsException(normalizedEmail);
        }

        UserEntity userEntity = new UserEntity(
                normalizedEmail,
                passwordEncoder.encode(userRegistrationRequest.password()),
                userRegistrationRequest.fullName().trim()
        );

        return UserResponse.from(userRepository.save(userEntity));
    }
}
