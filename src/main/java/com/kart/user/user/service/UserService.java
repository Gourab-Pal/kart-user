package com.kart.user.user.service;

import com.kart.user.common.dto.ExceptionResponse;
import com.kart.user.user.dto.LoginRequest;
import com.kart.user.user.dto.LoginResponse;
import com.kart.user.user.dto.UserRegistrationRequest;
import com.kart.user.user.dto.UserResponse;
import com.kart.user.user.entity.UserEntity;
import com.kart.user.user.exception.AccountDisabledException;
import com.kart.user.user.exception.EmailAlreadyExistsException;
import com.kart.user.user.exception.InvalidCredentialsException;
import com.kart.user.user.exception.UserNotFoundException;
import com.kart.user.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
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

    public LoginResponse login(LoginRequest request) {
        String email = request.email().trim().toLowerCase(Locale.ROOT);
        UserEntity user = userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new UserNotFoundException(email));


        if(!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        if(!"ACTIVE".equals(user.getStatus())) {
            throw new AccountDisabledException(user.getId());
        }

        String accessToken = tokenService.createAccessToken(user);

        return new LoginResponse(
                accessToken,
                "Bearer",
                tokenService.getExpirationSeconds(),
                UserResponse.from(user)
        );
    }
}
