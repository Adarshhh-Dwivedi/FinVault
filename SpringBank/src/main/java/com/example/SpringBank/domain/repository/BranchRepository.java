package com.example.SpringBank.domain.repository;

import com.example.SpringBank.domain.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository; // UPDATED: Switched from CrudRepository
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Modernized for SpringBank.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    /**
     * Find branches by city name.
     * Useful for customers looking for local branches.
     */
    List<Branch> findByCityIgnoreCase(String city);

    /**
     * Check if a branch exists by name.
     */
    boolean existsByName(String name);
}