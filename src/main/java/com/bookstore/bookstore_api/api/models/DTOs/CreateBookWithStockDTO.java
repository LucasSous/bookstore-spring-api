package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record CreateBookWithStockDTO(

        @NotNull
        @NotEmpty
        @Length(min = 3, max = 100)
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Clean Code")
        String name,

        @NotNull
        @NotEmpty
        @Length(min = 3, max = 100)
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Robert")
        String author,

        @NotNull
        @Digits(integer = 4, fraction = 4, message = "The year must contain 4 digits")
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "2024")
        int launchYear,

        @NotNull
        @Min(value = 1)
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
        int quantityAvailable,

        @NotNull
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "00000000000-0000-000-000-000000000000")
        UUID publisherId
) {
}
