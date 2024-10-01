package com.bookstore.bookstore_api.api.models.DTOs;

import com.bookstore.bookstore_api.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetUserDTO(
        @Schema(description = "User id", example = "00000000000-0000-000-000-000000000000")
        UUID userId,
        @Schema(description = "User name", example = "Lucas")
        String userName,
        @Schema(description = "E-mail", example = "email@email.com")
        String email,
        @Schema(description = "Role", example = "USER")
        RoleType role
) {
}
