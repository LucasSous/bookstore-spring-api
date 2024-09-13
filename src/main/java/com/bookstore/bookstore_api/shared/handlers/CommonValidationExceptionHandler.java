package com.bookstore.bookstore_api.shared.handlers;

import com.bookstore.bookstore_api.shared.exceptions.InvalidValueException;
import com.bookstore.bookstore_api.shared.exceptions.MinMaxException;
import com.bookstore.bookstore_api.shared.exceptions.ValueIsEmptyException;
import com.bookstore.bookstore_api.shared.models.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ValueIsEmptyException.class)
    public ExceptionDTO valueIsEmpty(ValueIsEmptyException ex){
        return new ExceptionDTO(ex.getMessage(), ex.getValue());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidValueException.class)
    public ExceptionDTO invalidValue(InvalidValueException ex){
        return new ExceptionDTO(ex.getMessage(), ex.getValue());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MinMaxException.class)
    public ExceptionDTO minMaxValue(MinMaxException ex){
        return new ExceptionDTO(ex.getMessage(), ex.getValue());
    }

}
