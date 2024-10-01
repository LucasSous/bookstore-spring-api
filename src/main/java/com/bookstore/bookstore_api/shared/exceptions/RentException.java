package com.bookstore.bookstore_api.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RentException extends RuntimeException{

    @Builder
    public RentException(String message) {
        super(message);
    }

}
