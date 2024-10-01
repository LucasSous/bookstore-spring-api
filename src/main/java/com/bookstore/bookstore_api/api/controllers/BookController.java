package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.annotations.*;
import com.bookstore.bookstore_api.api.models.DTOs.CreateBookWithStockDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateBookWithStockDTO;
import com.bookstore.bookstore_api.domain.services.BookService;
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
@OpenApiController(name = "Book Controller")
@ApiController(path = "/api/v1")
public class BookController {

    private final BookService bookService;

    @CreateBookEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@RequestBody @Validated CreateBookWithStockDTO body){
        bookService.create(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetBookByIdEndpoint
    public ResponseEntity getById(@PathVariable UUID id){
        var response = bookService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetAllBooksEndpoint
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam Integer items){
        var response = bookService.getAll(page, items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @UpdateBookEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody @Validated UpdateBookWithStockDTO body){
        bookService.update(id, body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteBookEndpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
