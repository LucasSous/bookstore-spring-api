package com.bookstore.bookstore_api.shared.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EntityDeletionException extends RuntimeException{
    @Builder
    public EntityDeletionException(String message){
        super(message);
    }
}
