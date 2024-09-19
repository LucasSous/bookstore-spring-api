package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.models.DTOs.CreatePublisherDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdatePublisherDTO;
import com.bookstore.bookstore_api.domain.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("/publisher")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@RequestBody @Validated CreatePublisherDTO body){
        publisherService.create(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/publisher/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getById(@PathVariable UUID id){
        var response = publisherService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/publishers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam Integer items){
        var response = publisherService.getAll(page, items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/publisher/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody UpdatePublisherDTO body ){
        publisherService.update(id, body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/publisher/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id){
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
