package com.bookstore.bookstore_api.api.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetPublisherDTO(
        @Schema(description = "Publisher id", example = "00000000000-0000-000-000-000000000000")
        UUID publisherId,

        @Schema(description = "Name", example = "HarperCollins")
        String name,

        @Schema(description = "City", example = "Fortaleza")
        String city
) {
}
