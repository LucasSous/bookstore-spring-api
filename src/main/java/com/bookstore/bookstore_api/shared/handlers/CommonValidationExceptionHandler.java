package com.bookstore.bookstore_api.shared.handlers;

import com.bookstore.bookstore_api.shared.exceptions.*;
import com.bookstore.bookstore_api.shared.models.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class CommonValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ValueIsEmptyException.class)
    public ExceptionDTO valueIsEmpty(ValueIsEmptyException ex){
        return new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidValueException.class)
    public ExceptionDTO invalidValue(InvalidValueException ex){
        return new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MinMaxException.class)
    public ExceptionDTO minMaxValue(MinMaxException ex){
        return new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ExceptionDTO notFound(NotFoundException ex){
        return new ExceptionDTO(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(AlreadyExistsException.class)
    public ExceptionDTO alreadyExists(AlreadyExistsException ex){
        return new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
