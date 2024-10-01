package com.bookstore.bookstore_api.domain.services;

import com.bookstore.bookstore_api.api.models.DTOs.CreateRentDTO;
import com.bookstore.bookstore_api.api.models.DTOs.GetRentDTO;
import com.bookstore.bookstore_api.api.models.DTOs.PagedResultDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateRentDTO;

import java.util.UUID;

public interface RentService {

    void create(CreateRentDTO body);

    GetRentDTO getById(UUID id);

    PagedResultDTO<GetRentDTO> getAll(Integer page, Integer items);

    void update(UUID id, UpdateRentDTO body);

    void delete(UUID id);

    void finalizeRent(UUID id);

}
