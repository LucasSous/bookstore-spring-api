package com.bookstore.bookstore_api.domain.validations;

import com.bookstore.bookstore_api.api.models.DTOs.CreateRentDTO;
import com.bookstore.bookstore_api.domain.models.entities.BookEntity;
import com.bookstore.bookstore_api.shared.exceptions.RentException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateRentValidator {

    public void validate(CreateRentDTO body, BookEntity book){
        LocalDate currentDate = LocalDate.now();
        if(body.expectedReturnDate().isBefore(currentDate)) throw new RentException("The expected return date cannot be less than the rental date");
        if(book.getBookStock().getQuantityAvailable() < 1) throw new RentException("The selected book is unavailable");
    }

}
