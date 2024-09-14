package com.bookstore.bookstore_api.domain.validations;

import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;
import com.bookstore.bookstore_api.domain.models.enums.RoleType;
import com.bookstore.bookstore_api.shared.exceptions.InvalidEmailException;
import com.bookstore.bookstore_api.shared.exceptions.InvalidValueException;
import com.bookstore.bookstore_api.shared.exceptions.MinMaxException;
import com.bookstore.bookstore_api.shared.exceptions.ValueIsEmptyException;
import org.springframework.stereotype.Component;

@Component
public class CreateUserValidator {

    public void validate(CreateUserDTO createUserDTO){
        var userName = createUserDTO.getUserName();
        var email = createUserDTO.getEmail();
        var password = createUserDTO.getPassword();
        var role = createUserDTO.getRole();
        if (userName == null || userName.isBlank()) {
            throw new ValueIsEmptyException("The value of the userName parameter cannot be null or empty");
        } else if (userName.length() < 3 || userName.length() > 100) {
            throw new MinMaxException("The value of the userName parameter must be between 3 and 100 characters");
        } else if (isValueValid(userName)) {
            throw new InvalidValueException("Invalid userName format");
        }
        if (email == null || email.isBlank()) {
            throw new ValueIsEmptyException("The value of the email parameter cannot be null or empty");
        } else if(isEmailValid(email)) {
            throw new InvalidEmailException("Invalid email format");
        } else if (email.length() > 100) {
            throw new MinMaxException("The value of the email parameter must have a maximum of 100 characters");
        }
        if(password == null || password.isBlank()){
            throw new ValueIsEmptyException("The value of the password parameter cannot be null or empty");
        }
        if(hasSpaces(password)) {
            throw new InvalidValueException("Invalid password");
        }
        if(role != RoleType.ADMIN && role != RoleType.USER) {
            throw new InvalidValueException("Invalid user role");
        }
    }

    private boolean isEmailValid(String email) {
        boolean isValid;
        isValid = !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return isValid;
    }

    private boolean isValueValid(String value) {
        boolean isValid;
        isValid = !value.matches("^[a-zA-Z]+(?: [a-zA-Z]+)*$");
        return isValid;
    }

    private boolean hasSpaces(String value) {
        boolean hasSpaces;
        hasSpaces = value.matches(".*" + "\\s" + ".*");
        return hasSpaces;
    }

}
