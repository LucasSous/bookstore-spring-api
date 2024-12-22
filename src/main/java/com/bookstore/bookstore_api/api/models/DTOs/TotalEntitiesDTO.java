package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TotalEntitiesDTO (
        @Schema(description = "Total Books", example = "100")
        int totalBooks,
        @Schema(description = "Total Users", example = "100")
        int totalUsers,
        @Schema(description = "Total Publishers", example = "100")
        int totalPublishers,
        @Schema(description = "Total Rents", example = "100")
        int totalRents
) {
}
