package com.example.SpringBank.exception;

import java.util.Map;

/**
 * Thrown when an attempt is made to create an entity that already exists.
 * Modernized for SpringBank.
 */
public class DuplicateEntityException extends EntityException {

    /**
     * @param clazz The entity class (e.g., Customer.class)
     * @param searchParamsMap The key-value pairs that are duplicates (e.g., "ssn", "123-45-6789")
     */
    public DuplicateEntityException(Class<?> clazz, String... searchParamsMap) {
        super(clazz, " was already found for parameters ", searchParamsMap);
    }
}