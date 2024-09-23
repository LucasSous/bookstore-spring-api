package com.bookstore.bookstore_api.domain.services.impl;

import com.bookstore.bookstore_api.api.models.DTOs.*;
import com.bookstore.bookstore_api.domain.models.entities.PublisherEntity;
import com.bookstore.bookstore_api.domain.repositories.PublisherEntityRepository;
import com.bookstore.bookstore_api.domain.services.PublisherService;
import com.bookstore.bookstore_api.shared.exceptions.AlreadyExistsException;
import com.bookstore.bookstore_api.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherEntityRepository publisherRepository;

    @Override
    public void create(CreatePublisherDTO body) {
        Optional<PublisherEntity> existingPublisher = publisherRepository.findByName(body.name());

        if(existingPublisher.isPresent()) throw new AlreadyExistsException("Publisher already exists");

        var newPublisher = buildPublisherEntity(body);
        publisherRepository.save(newPublisher);
    }

    @Override
    public GetPublisherDTO getById(UUID id) {
        PublisherEntity publisher = publisherRepository.findById(id).orElseThrow(() -> new NotFoundException("Publisher not found"));
        return buildGetPublisherDTO(publisher);
    }

    @Override
    public PagedResultDTO<GetPublisherDTO> getAll(Integer page, Integer items) {
        Pageable pageRequest = PageRequest.of(page, items);
        Page<PublisherEntity> pagedResult = publisherRepository.findAll(pageRequest);
        return buildPagedResponseDTO(pagedResult);
    }

    @Override
    public void update(UUID id, UpdatePublisherDTO body) {
        PublisherEntity publisher = publisherRepository.findById(id).orElseThrow(() -> new NotFoundException("Publisher not found"));
        if (!publisher.getName().equals(body.name())) {
            Optional<PublisherEntity> existingPublisher = publisherRepository.findByName(body.name());
            if(existingPublisher.isPresent()) throw new AlreadyExistsException("Publisher already exists");
        }
        publisher.setName(body.name());
        publisher.setCity(body.city());
        publisherRepository.save(publisher);
    }

    @Override
    public void delete(UUID id) {
        PublisherEntity publisher = publisherRepository.findById(id).orElseThrow(() -> new NotFoundException("Publisher not found"));

        publisherRepository.deleteById(publisher.getId());
    }

    private PublisherEntity buildPublisherEntity(CreatePublisherDTO createPublisherDTO){
        return PublisherEntity.builder()
                .name(createPublisherDTO.name())
                .city(createPublisherDTO.city())
                .build();
    }

    private GetPublisherDTO buildGetPublisherDTO(PublisherEntity entity){
        return GetPublisherDTO.builder()
                .publisherId(entity.getId())
                .name(entity.getName())
                .city(entity.getCity())
                .build();
    }

    private List<GetPublisherDTO> buildGetPublisherDTOList(Page<PublisherEntity> pagedResult){
        List<GetPublisherDTO> list = new ArrayList<>();
        for (PublisherEntity publisherEntity : pagedResult.stream().toList()) {
            GetPublisherDTO getPublisherDTO = buildGetPublisherDTO(publisherEntity);
            list.add(getPublisherDTO);
        }

        return list;
    }

    private PagedResultDTO<GetPublisherDTO> buildPagedResponseDTO(Page<PublisherEntity> page) {
        return PagedResultDTO.<GetPublisherDTO>builder()
                .data(buildGetPublisherDTOList(page))
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .pageSize(page.getSize())
                .build();
    }
}
