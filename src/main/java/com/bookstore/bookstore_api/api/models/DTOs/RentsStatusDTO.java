package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RentsStatusDTO (
        @Schema(description = "Total Active", example = "100")
        int totalActive,
        @Schema(description = "Total Delayed", example = "100")
        int totalDelayed,
        @Schema(description = "Total Ended", example = "100")
        int totalEnded
) {
}
