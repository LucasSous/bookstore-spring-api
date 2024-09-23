package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetBookDTO (
        @Schema(description = "Book Id", example = "00000000000-0000-000-000-000000000000")
        UUID id,
        @Schema(description = "Name", example = "")
        String name,
        @Schema(description = "Author", example = "")
        String author,
        @Schema(description = "Publisher Id", example = "00000000000-0000-000-000-000000000000")
        UUID publisherId,
        @Schema(description = "Launch year", example = "2024")
        int launchYear,
        BookStockDTO bookStock
) {
}
