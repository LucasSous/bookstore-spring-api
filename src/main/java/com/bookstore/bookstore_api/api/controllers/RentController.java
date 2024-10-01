package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.annotations.*;
import com.bookstore.bookstore_api.api.models.DTOs.CreateRentDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateRentDTO;
import com.bookstore.bookstore_api.domain.services.RentService;
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
@OpenApiController(name = "Rent Controller")
@ApiController(path = "/api/v1")
public class RentController {

    private final RentService rentService;

    @CreateRentEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@RequestBody @Validated CreateRentDTO body){
        rentService.create(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetRentByIdEndpoint
    public ResponseEntity getById(@PathVariable UUID id){
        var response = rentService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetAllRentsEndpoint
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam Integer items){
        var response = rentService.getAll(page, items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @UpdateRentEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody @Validated UpdateRentDTO body ){
        rentService.update(id, body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @FinalizeRentEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity finalizeRent(@PathVariable UUID id){
        rentService.finalizeRent(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteRentEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id){
        rentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
