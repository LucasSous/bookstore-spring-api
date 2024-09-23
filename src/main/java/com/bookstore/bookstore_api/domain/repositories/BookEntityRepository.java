package com.bookstore.bookstore_api.domain.repositories;

import com.bookstore.bookstore_api.domain.models.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookEntityRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByName(String name);
}
