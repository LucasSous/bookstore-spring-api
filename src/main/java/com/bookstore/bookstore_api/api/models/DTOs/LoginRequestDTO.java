package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRequestDTO (
        @Schema(description = "Username.", example = "username")
        @NotEmpty
        @NotNull
        String email,
        @Schema(description = "Password.", example = "password")
        @NotEmpty
        @NotNull
        String password
){
}
