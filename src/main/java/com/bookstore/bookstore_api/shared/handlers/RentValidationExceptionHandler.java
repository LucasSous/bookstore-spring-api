package com.bookstore.bookstore_api.shared.handlers;

import com.bookstore.bookstore_api.shared.exceptions.RentException;
import com.bookstore.bookstore_api.shared.models.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RentValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(RentException.class)
    public ExceptionDTO rentException(RentException ex) {
        return new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
