package com.bookstore.bookstore_api.domain.repositories;

import com.bookstore.bookstore_api.domain.models.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherEntity, UUID> {
    Optional<PublisherEntity> findByName(String name);
}
