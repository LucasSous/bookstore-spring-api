package com.bookstore.bookstore_api.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MinMaxException extends RuntimeException {
    private String value;

    @Builder
    public MinMaxException(String message, String value) {
        super(message);
        this.value = value;
    }
}
