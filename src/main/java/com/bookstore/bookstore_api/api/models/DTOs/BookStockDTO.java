package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BookStockDTO(
        @Schema(description = "Book Stock Id", example = "00000000000-0000-000-000-000000000000")
        UUID id,

        @Schema(description = "Book Id", example = "00000000000-0000-000-000-000000000000")
        UUID bookId,

        @Schema(description = "Quantity Available", example = "100")
        int quantityAvailable,

        @Schema(description = "Quantity Rented", example = "30")
        int quantityRented
) {
}
