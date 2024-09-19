package com.bookstore.bookstore_api.domain.services;

import com.bookstore.bookstore_api.api.models.DTOs.*;

import java.util.UUID;

public interface PublisherService {
    void create(CreatePublisherDTO body);

    GetPublisherDTO getById(UUID id);

    PagedResultDTO<GetPublisherDTO> getAll(Integer page, Integer items);

    void update(UUID id, UpdatePublisherDTO body);

    void delete(UUID id);
}
