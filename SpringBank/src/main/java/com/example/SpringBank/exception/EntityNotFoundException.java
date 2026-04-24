package com.example.FinVault.exception;

/**
 * Thrown when a requested resource does not exist in the database.
 * Modernized for the FinVault project structure.
 */
public class EntityNotFoundException extends EntityException {

    /**
     * @param clazz The entity class (e.g., Account.class)
     * @param searchParamsMap The criteria used for the search (e.g., "accountNumber", "5544")
     */
    public EntityNotFoundException(Class<?> clazz, String... searchParamsMap) {
        super(clazz, " was not found for parameters ", searchParamsMap);
    }
}