package com.bookstore.bookstore_api.shared.handlers;

import com.bookstore.bookstore_api.shared.exceptions.ValueIsEmptyException;
import com.bookstore.bookstore_api.shared.models.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthValidationExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(ValueIsEmptyException.class)
    public ExceptionDTO unauthorized(ValueIsEmptyException ex){
        return new ExceptionDTO(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

}
