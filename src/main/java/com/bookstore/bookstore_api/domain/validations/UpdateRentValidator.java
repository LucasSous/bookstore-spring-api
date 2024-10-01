package com.bookstore.bookstore_api.domain.validations;

import com.bookstore.bookstore_api.api.models.DTOs.UpdateRentDTO;
import com.bookstore.bookstore_api.domain.models.entities.RentEntity;
import com.bookstore.bookstore_api.shared.exceptions.RentException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UpdateRentValidator {

    public void validate(UpdateRentDTO body, RentEntity rentEntity){
        if(body.expectedReturnDate().isBefore(rentEntity.getCreatedAt().toLocalDate())) {
            throw new RentException("The expected return date cannot be less than the rental date");
        }
    }

}
