package com.bookstore.bookstore_api.domain.services;

import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;

public interface UserService {
    void create(CreateUserDTO body);
}
