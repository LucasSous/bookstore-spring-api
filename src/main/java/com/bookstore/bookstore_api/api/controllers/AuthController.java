package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.annotations.LoginEndpoint;
import com.bookstore.bookstore_api.api.models.DTOs.LoginRequestDTO;
import com.bookstore.bookstore_api.domain.services.AuthService;
import com.bookstore.bookstore_api.shared.web.ApiController;
import com.bookstore.bookstore_api.shared.web.OpenApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@OpenApiController(name = "Auth Controller")
@ApiController(path = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @LoginEndpoint
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        var response = authService.login(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
