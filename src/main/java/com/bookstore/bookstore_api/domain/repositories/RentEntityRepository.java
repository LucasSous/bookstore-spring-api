package com.bookstore.bookstore_api.domain.repositories;

import com.bookstore.bookstore_api.domain.models.entities.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentEntityRepository extends JpaRepository<RentEntity, UUID> {
}
