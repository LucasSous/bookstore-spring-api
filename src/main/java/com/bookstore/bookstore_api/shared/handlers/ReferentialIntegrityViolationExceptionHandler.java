package com.bookstore.bookstore_api.shared.handlers;

import com.bookstore.bookstore_api.shared.exceptions.EntityDeletionException;
import com.bookstore.bookstore_api.shared.models.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ReferentialIntegrityViolationExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler(EntityDeletionException.class)
    public ExceptionDTO entityDeletionException(EntityDeletionException ex) {
        return new ExceptionDTO(HttpStatus.CONFLICT, ex.getMessage());
    }
}
