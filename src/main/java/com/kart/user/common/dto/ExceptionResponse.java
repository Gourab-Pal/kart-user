package com.kart.user.common.dto;

import java.time.OffsetDateTime;

public record ExceptionResponse(
        String message,
        OffsetDateTime timestamp
) {
}
