package com.bookstore.bookstore_api.domain.validations;

import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;
import com.bookstore.bookstore_api.shared.exceptions.InvalidValueException;
import com.bookstore.bookstore_api.shared.utils.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class CreateUserValidator {

    public void validate(CreateUserDTO createUserDTO){
        var userName = createUserDTO.getUserName();
        var password = createUserDTO.getPassword();
        if (ValidationUtil.isValueValid(userName)) {
            throw new InvalidValueException("Invalid userName format");
        }
        if(ValidationUtil.hasSpaces(password)) {
            throw new InvalidValueException("Invalid password");
        }
    }

}
