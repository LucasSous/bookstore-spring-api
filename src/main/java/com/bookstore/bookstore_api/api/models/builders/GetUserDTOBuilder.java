package com.bookstore.bookstore_api.api.models.builders;

import com.bookstore.bookstore_api.api.models.DTOs.GetUserDTO;
import com.bookstore.bookstore_api.domain.models.enums.RoleType;

import java.util.UUID;

public final class GetUserDTOBuilder {
    private UUID userId;

    public GetUserDTOBuilder userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    private String userName;

    public GetUserDTOBuilder userName(String userName){
        this.userName = userName;
        return this;
    }

    private String email;

    public GetUserDTOBuilder email(String email){
        this.email = email;
        return this;
    }

    private RoleType role;

    public GetUserDTOBuilder role(RoleType role){
        this.role = role;
        return this;
    }

    public GetUserDTO build() {
        return new GetUserDTO(userId, userName, email, role);
    }
}
