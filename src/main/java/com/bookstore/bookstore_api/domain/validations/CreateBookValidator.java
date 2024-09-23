package com.bookstore.bookstore_api.domain.validations;

import com.bookstore.bookstore_api.api.models.DTOs.CreateBookWithStockDTO;
import com.bookstore.bookstore_api.shared.exceptions.InvalidValueException;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class CreateBookValidator {

    public void validator(CreateBookWithStockDTO createBookWithStockDTO){
        int currentYear = Year.now().getValue();
        if(currentYear < createBookWithStockDTO.launchYear()) {
            throw new InvalidValueException(String.format("The launch year value cannot be greater than %s", currentYear));
        }
    }

}
