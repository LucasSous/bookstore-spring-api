package com.bookstore.bookstore_api.shared.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnauthorizedException extends RuntimeException{
    @Builder
    public UnauthorizedException(String message) {
        super(message);
    }
}
