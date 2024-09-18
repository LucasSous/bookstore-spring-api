package com.bookstore.bookstore_api.shared.handlers;

import com.bookstore.bookstore_api.shared.exceptions.UnauthorizedException;
import com.bookstore.bookstore_api.shared.models.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthValidationExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public ExceptionDTO unauthorized(UnauthorizedException ex){
        return new ExceptionDTO(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ExceptionDTO accessDenied(AccessDeniedException ex){
        return new ExceptionDTO(HttpStatus.FORBIDDEN, ex.getMessage());
    }

}
