package com.bookstore.bookstore_api.domain.models.entities;

import com.bookstore.bookstore_api.shared.models.enums.RoleType;
import com.bookstore.bookstore_api.shared.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 10)
    private RoleType role;

    @Builder(toBuilder = true)
    public UserEntity(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String userName, String email, String password, RoleType role){
        super(id, createdAt, updatedAt);
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
