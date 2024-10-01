package com.bookstore.bookstore_api.shared.handlers;
import com.bookstore.bookstore_api.shared.models.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
@ControllerAdvice
public class GlobalValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDTO methodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ExceptionDTO(HttpStatus.BAD_REQUEST, errors);
    }
}
