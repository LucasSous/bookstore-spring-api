package com.bookstore.bookstore_api.domain.services;

import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;
import com.bookstore.bookstore_api.api.models.DTOs.GetUserDTO;
import com.bookstore.bookstore_api.api.models.DTOs.PagedResultDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateUserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void create(CreateUserDTO body);

    GetUserDTO getById(String token, UUID id);

    PagedResultDTO<GetUserDTO> getAll(Integer page, Integer items);

    void update(String token, UUID id, UpdateUserDTO body);

    void delete(String token, UUID id);
}
