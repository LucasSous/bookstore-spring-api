package com.bookstore.bookstore_api.shared.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDTO {
    private int status;
    private String error;
    private Map<String, String> errors;
    private String message;
    private String timestamp;

    public ExceptionDTO(HttpStatus status, String message) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ExceptionDTO(HttpStatus status, Map<String, String> errors) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.errors = errors;
        this.timestamp = LocalDateTime.now().toString();
    }
}

