package com.bookstore.bookstore_api.domain.models.entities;

import com.bookstore.bookstore_api.shared.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book_stocks")
public class BookStockEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;

    @Column(name = "quantity_available", nullable = false)
    private int quantityAvailable;

    @Column(name = "quantity_rented", nullable = false)
    private int quantityRented;

    @Builder(toBuilder = true)
    public BookStockEntity(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, BookEntity book, int quantityAvailable, int quantityRented) {
        super(id, createdAt, updatedAt);
        this.book = book;
        this.quantityAvailable = quantityAvailable;
        this.quantityRented = quantityRented;
    }
}
