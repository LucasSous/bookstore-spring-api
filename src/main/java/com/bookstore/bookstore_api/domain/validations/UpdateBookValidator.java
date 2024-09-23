package com.bookstore.bookstore_api.domain.validations;

import com.bookstore.bookstore_api.api.models.DTOs.UpdateBookWithStockDTO;
import com.bookstore.bookstore_api.shared.exceptions.InvalidValueException;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class UpdateBookValidator {

    public void validator(UpdateBookWithStockDTO updateBookWithStockDTO){
        int currentYear = Year.now().getValue();
        if(currentYear < updateBookWithStockDTO.launchYear()) {
            throw new InvalidValueException(String.format("The launch year value cannot be greater than %s", currentYear));
        }
    }

}
