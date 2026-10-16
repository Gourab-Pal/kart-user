package com.kart.user.common.exception;

import com.kart.user.common.dto.ExceptionResponse;
import com.kart.user.user.exception.AccountDisabledException;
import com.kart.user.user.exception.EmailAlreadyExistsException;
import com.kart.user.user.exception.InvalidCredentialsException;
import com.kart.user.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        log.error(exception.getMessage());
        return new ExceptionResponse(exception.getMessage(), OffsetDateTime.now());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleInvalidCredentialsException(InvalidCredentialsException exception) {
        log.error(exception.getMessage());
        return new ExceptionResponse(exception.getMessage(), OffsetDateTime.now());
    }

    @ExceptionHandler(AccountDisabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleAccountDisabledException(AccountDisabledException exception) {
        log.error(exception.getMessage());
        return new ExceptionResponse(exception.getMessage(), OffsetDateTime.now());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage());
        return new ExceptionResponse(exception.getMessage(), OffsetDateTime.now());
    }
}
