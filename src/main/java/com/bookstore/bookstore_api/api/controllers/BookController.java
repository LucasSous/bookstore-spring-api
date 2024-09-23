package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.models.DTOs.CreateBookWithStockDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateBookWithStockDTO;
import com.bookstore.bookstore_api.domain.services.BookService;
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
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@RequestBody @Validated CreateBookWithStockDTO body){
        bookService.create(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/book/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getById(@PathVariable UUID id){
        var response = bookService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam Integer items){
        var response = bookService.getAll(page, items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/book/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody @Validated UpdateBookWithStockDTO body){
        bookService.update(id, body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable UUID id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
