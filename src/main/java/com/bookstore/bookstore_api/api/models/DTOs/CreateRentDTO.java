package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateRentDTO(
        @NotNull
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "00000000000-0000-000-000-000000000000")
        UUID userId,
        @NotNull
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "00000000000-0000-000-000-000000000000")
        UUID bookId,
        @NotNull
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-09-23")
        LocalDate expectedReturnDate
) {
}
