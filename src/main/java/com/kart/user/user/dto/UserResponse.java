package com.kart.user.user.dto;

import com.kart.user.user.entity.UserEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String fullName,
        String role,
        String status,
        OffsetDateTime createdAt
) {
    public static UserResponse from(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFullName(),
                userEntity.getRole(),
                userEntity.getStatus(),
                userEntity.getCreatedAt()
        );
    }
}
