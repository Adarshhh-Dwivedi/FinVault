package com.example.SpringBank.domain.model;

import jakarta.persistence.*; // UPDATED: Changed from javax to jakarta
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Modernized for SpringBank
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "branches") // Explicit table naming
public class Branch extends BaseDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    // Ensuring precision for large financial assets
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal assets = BigDecimal.ZERO;
}