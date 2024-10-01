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

    @Column(name = "number_of_times_rented", nullable = false)
    private int numberOfTimesRented;

    @Builder(toBuilder = true)
    public BookStockEntity(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, BookEntity book, int quantityAvailable, int quantityRented, int numberOfTimesRented) {
        super(id, createdAt, updatedAt);
        this.book = book;
        this.quantityAvailable = quantityAvailable;
        this.quantityRented = quantityRented;
        this.numberOfTimesRented = numberOfTimesRented;
    }

    public void changeValuesAfterRent(){
        if(this.quantityAvailable > 0) {
          this.quantityAvailable = this.quantityAvailable - 1;
          this.quantityRented = this.quantityRented + 1;
          this.numberOfTimesRented = numberOfTimesRented + 1;
        };
    }

    public void changeValuesAfterFinalizingRent(){
            this.quantityAvailable = this.quantityAvailable + 1;
            this.quantityRented = this.quantityRented - 1;
    }
}
