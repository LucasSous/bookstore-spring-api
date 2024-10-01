package com.bookstore.bookstore_api.api.models.DTOs;

import com.bookstore.bookstore_api.shared.models.enums.RentStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record GetRentDTO(
        UUID id,
        UUID bookId,
        String bookName,
        UUID userId,
        String userName,
        LocalDate rentalDate,
        LocalDate expectedReturnDate,
        LocalDate returnDate,
        RentStatus rentStatus
) {
}
