package com.bookstore.bookstore_api.domain.models.entities;

import com.bookstore.bookstore_api.shared.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rents")
public class RentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expectedReturnDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Builder(toBuilder = true)
    public RentEntity(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, BookEntity book, UserEntity user, LocalDate expectedReturnDate, LocalDate returnDate){
        super(id, createdAt, updatedAt);
        this.book = book;
        this.user = user;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
    }
}
