package com.bookstore.bookstore_api.domain.validations;

import com.bookstore.bookstore_api.api.models.DTOs.UpdateUserDTO;
import com.bookstore.bookstore_api.shared.exceptions.InvalidValueException;
import com.bookstore.bookstore_api.shared.utils.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserValidator {
    public void validate(UpdateUserDTO updateUserDTO){
        var userName = updateUserDTO.getUserName();
        if (ValidationUtil.isValueValid(userName)) {
            throw new InvalidValueException("Invalid userName format");
        }
    }
}
