package com.kart.user.common.exception;

import com.kart.user.common.dto.ExceptionResponse;
import com.kart.user.user.exception.EmailAlreadyExistsException;
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
}
