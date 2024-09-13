package com.bookstore.bookstore_api.api.models.builders;

import com.bookstore.bookstore_api.api.models.DTOs.LoginResponseDTO;
import com.bookstore.bookstore_api.domain.models.enums.RoleType;

import java.util.UUID;

public final class LoginResponseDTOBuilder{
    private UUID userId;

    public LoginResponseDTOBuilder userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    private String userName;

    public LoginResponseDTOBuilder userName(String userName){
        this.userName = userName;
        return this;
    }

    private String token;

    public LoginResponseDTOBuilder token(String token){
        this.token = token;
        return this;
    }

    private RoleType role;

    public LoginResponseDTOBuilder role(RoleType role){
        this.role = role;
        return this;
    }

    public LoginResponseDTO build() {
        return new LoginResponseDTO(userId, userName, token, role);
    }

}
