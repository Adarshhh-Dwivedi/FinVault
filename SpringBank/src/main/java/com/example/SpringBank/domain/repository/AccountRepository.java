package com.example.SpringBank.domain.repository;

import com.example.SpringBank.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository; // UPDATED: More features than CrudRepository
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Modernized for SpringBank.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Find an account by the account number.
     * Note: Since accountNumber is the @Id, findById() does this,
     * but defining this can make the Service layer code more readable.
     */
    Optional<Account> findByAccountNumber(Long accountNumber);

    // Potential add-on: Find all accounts for a specific customer
    // List<Account> findByAccountOwnerCustomerId(Long customerId);
}