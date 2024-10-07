package com.bookstore.bookstore_api.domain.repositories;

import com.bookstore.bookstore_api.domain.models.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BookEntityRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByName(String name);

    @Query("SELECT b FROM BookEntity b WHERE b.bookStock.quantityAvailable > 0")
    Page<BookEntity> findAllAvailable(Pageable pageable);

    @Query("SELECT b FROM BookEntity b JOIN b.bookStock bs WHERE bs.numberOfTimesRented > 0 ORDER BY bs.numberOfTimesRented DESC")
    Page<BookEntity> findTop5MostRented(Pageable pageable);
}
