package com.bookstore.bookstore_api.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotFoundException extends RuntimeException{
    @Builder
    public NotFoundException(String message) {
        super(message);
    }
}
