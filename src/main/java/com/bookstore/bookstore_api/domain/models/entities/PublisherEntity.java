package com.bookstore.bookstore_api.domain.models.entities;

import com.bookstore.bookstore_api.shared.models.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "publishers")
public class PublisherEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Builder(toBuilder = true)
    public PublisherEntity(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String name, String city){
        super(id, createdAt, updatedAt);
        this.name = name;
        this.city = city;
    }
}
