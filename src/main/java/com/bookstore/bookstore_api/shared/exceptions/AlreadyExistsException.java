package com.bookstore.bookstore_api.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlreadyExistsException extends RuntimeException {
    @Builder
    public AlreadyExistsException(String message){
        super(message);
    }
}
