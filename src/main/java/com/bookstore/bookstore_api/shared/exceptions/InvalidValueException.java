package com.bookstore.bookstore_api.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvalidValueException extends RuntimeException{

    @Builder
    public InvalidValueException(String message) {
        super(message);
    }
}
