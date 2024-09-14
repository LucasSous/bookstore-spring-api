package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.models.DTOs.LoginRequestDTO;
import com.bookstore.bookstore_api.domain.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        var response = authService.login(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
