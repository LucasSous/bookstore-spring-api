package com.bookstore.bookstore_api.api.models.DTOs;

import com.bookstore.bookstore_api.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record LoginResponseDTO(
        @Schema(description = "User id", example = "00000000000-0000-000-000-000000000000")
        UUID userId,
        @Schema(description = "User name", example = "Lucas")
        String userName,
        @Schema(description = "Token", example = "ekJhbGciZiJIUzI1niJ4")
        String token,
        @Schema(description = "Role", example = "USER")
        RoleType role
){
}
