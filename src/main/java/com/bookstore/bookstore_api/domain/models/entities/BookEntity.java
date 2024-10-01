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
@Table(name = "books")
public class BookEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "launch_year", nullable = false)
    private int launchYear;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private PublisherEntity publisher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_stock_id", referencedColumnName = "id")
    private BookStockEntity bookStock;

    @Builder(toBuilder = true)
    public BookEntity(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String name, String author, int launchYear, PublisherEntity publisher, BookStockEntity bookStock) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.author = author;
        this.launchYear = launchYear;
        this.publisher = publisher;
        this.bookStock = bookStock;
    }
}
