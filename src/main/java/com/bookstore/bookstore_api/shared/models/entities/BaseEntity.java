package com.bookstore.bookstore_api.shared.models.entities;

import com.bookstore.bookstore_api.shared.utils.DateTimeUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    protected UUID id;
    @Column(name = "created_at", nullable = false)
    protected OffsetDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    protected OffsetDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        createdAt = updatedAt = DateTimeUtil.nowZoneUTC();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = DateTimeUtil.nowZoneUTC();
    }
}