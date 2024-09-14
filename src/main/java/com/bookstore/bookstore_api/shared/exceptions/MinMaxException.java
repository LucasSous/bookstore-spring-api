package com.bookstore.bookstore_api.shared.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MinMaxException extends RuntimeException {
    @Builder
    public MinMaxException(String message) {
        super(message);
    }
}
