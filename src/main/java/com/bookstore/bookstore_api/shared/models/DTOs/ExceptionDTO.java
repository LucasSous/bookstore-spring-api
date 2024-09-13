package com.bookstore.bookstore_api.shared.models.DTOs;

public record ExceptionDTO(
        String message,
        String value
) {
}
