package com.bookstore.bookstore_api.api.models.DTOs;

import com.bookstore.bookstore_api.domain.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CreateUser")
public class CreateUserDTO {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Lucas")
    private String userName;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "email@gmail.com")
    private String email;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Admin123@")
    private String password;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ADMIN")
    private RoleType role;
}
