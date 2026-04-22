package com.example.SpringBank.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Common properties for all SpringBank domain objects.
 * Updated to use Java 21 Time API.
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDomainObject implements Serializable {

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    protected OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected OffsetDateTime updatedAt;
}