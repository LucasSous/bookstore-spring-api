package com.bookstore.bookstore_api.api.models.DTOs;

import com.bookstore.bookstore_api.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CreateUser")
public class CreateUserDTO {
    @NotNull
    @NotEmpty
    @Length(min = 3, max = 100)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Lucas")
    private String userName;

    @NotNull
    @NotEmpty
    @Email
    @Length(max = 100)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "email@gmail.com")
    private String email;

    @NotNull
    @NotEmpty
    @Length(max = 100)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Admin123@")
    private String password;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ADMIN")
    private RoleType role;
}
