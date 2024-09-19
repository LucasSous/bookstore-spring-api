package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreatePublisherDTO(
        @NotNull
        @NotEmpty
        @Length(min = 3, max = 100)
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "HarperCollins")
        String name,

        @NotNull
        @NotEmpty
        @Length(min = 3, max = 100)
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Fortaleza")
        String city
) {
}
