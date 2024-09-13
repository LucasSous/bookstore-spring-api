package com.bookstore.bookstore_api.api.models.DTOs;

import com.bookstore.bookstore_api.api.models.builders.LoginResponseDTOBuilder;
import com.bookstore.bookstore_api.domain.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.UUID;

public record LoginResponseDTO(
        @Schema(description = "User id", example = "00000000000-0000-000-000-000000000000")
        UUID userId,
        @Schema(description = "User name", example = "Lucas")
        String userName,
        @Schema(description = "Token", example = "ekJhbGciZiJIUzI1niJ4")
        String token,
        @Schema(description = "Role", example = "USER")
        RoleType role
) implements Serializable {
    public static LoginResponseDTOBuilder builder() {return new LoginResponseDTOBuilder();};
}
