package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.annotations.*;
import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateUserDTO;
import com.bookstore.bookstore_api.domain.services.UserService;
import com.bookstore.bookstore_api.shared.web.ApiController;
import com.bookstore.bookstore_api.shared.web.OpenApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@OpenApiController(name = "User Controller")
@ApiController(path = "/api/v1")
public class UserController {

    private final UserService userService;

    @CreateUserEndpoint
    public ResponseEntity create(@RequestBody @Validated CreateUserDTO body){
        userService.create(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetUserByIdEndpoint
    public ResponseEntity getById(@RequestHeader("Authorization") String token, @PathVariable UUID id){
        var response = userService.getById(token, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetAllUsersEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam Integer items){
        var response = userService.getAll(page, items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @UpdateUserEndpoint
    public ResponseEntity update(@RequestHeader("Authorization") String token, @PathVariable UUID id, @RequestBody @Validated UpdateUserDTO body){
        userService.update(token, id, body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteUserEndpoint
    public ResponseEntity delete(@RequestHeader("Authorization") String token, @PathVariable UUID id){
        userService.delete(token, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
