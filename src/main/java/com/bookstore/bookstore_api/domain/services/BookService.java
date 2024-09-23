package com.bookstore.bookstore_api.domain.services;

import com.bookstore.bookstore_api.api.models.DTOs.CreateBookWithStockDTO;
import com.bookstore.bookstore_api.api.models.DTOs.GetBookDTO;
import com.bookstore.bookstore_api.api.models.DTOs.PagedResultDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateBookWithStockDTO;

import java.util.UUID;

public interface BookService {
    void create(CreateBookWithStockDTO body);

    GetBookDTO getById(UUID id);

    PagedResultDTO<GetBookDTO> getAll(Integer page, Integer items);

    void update(UUID id, UpdateBookWithStockDTO body);

    void delete(UUID id);
}
