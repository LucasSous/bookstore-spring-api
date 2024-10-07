package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.annotations.*;
import com.bookstore.bookstore_api.api.models.DTOs.CreatePublisherDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdatePublisherDTO;
import com.bookstore.bookstore_api.domain.services.PublisherService;
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
@OpenApiController(name = "Publisher Controller")
@ApiController(path = "/api/v1")
public class PublisherController {

    private final PublisherService publisherService;

    @CreatePublisherEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@RequestBody @Validated CreatePublisherDTO body){
        publisherService.create(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetPublisherByIdEndpoint
    public ResponseEntity getById(@PathVariable UUID id){
        var response = publisherService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetAllPublishersEndpoint
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer items){
        var response = publisherService.getAll(page, items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @UpdatePublisherEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody @Validated UpdatePublisherDTO body ){
        publisherService.update(id, body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeletePublisherEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id){
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
