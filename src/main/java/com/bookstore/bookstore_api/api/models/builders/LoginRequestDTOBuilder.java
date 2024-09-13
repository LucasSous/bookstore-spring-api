package com.bookstore.bookstore_api.api.models.builders;

import com.bookstore.bookstore_api.api.models.DTOs.LoginRequestDTO;

public final class LoginRequestDTOBuilder {
    private String email;

    public LoginRequestDTOBuilder email(String email){
        this.email = email;
        return this;
    }

    private String password;

    public LoginRequestDTOBuilder password(String password){
        this.password = password;
        return this;
    }

    public LoginRequestDTO build() {
        return new LoginRequestDTO(email, password);
    }
}
