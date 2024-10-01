package com.bookstore.bookstore_api.shared.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
