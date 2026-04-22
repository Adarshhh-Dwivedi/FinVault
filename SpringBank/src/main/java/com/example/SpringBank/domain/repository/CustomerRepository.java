package com.example.SpringBank.domain.repository;

import com.example.SpringBank.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository; // UPDATED: More robust than CrudRepository
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Modernized for SpringBank.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds a customer by their unique Social Security Number.
     * Updated to return Optional for safer null handling in 2026 standards.
     */
    Optional<Customer> findBySsn(String ssn);

    /**
     * Checks if a customer exists with the given SSN.
     * Useful for validation during the registration process.
     */
    boolean existsBySsn(String ssn);
}