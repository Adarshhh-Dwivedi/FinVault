package com.example.SpringBank.service.account.exception;

import com.example.FinVault.exception.BankException;

/**
 * Modernized Base Exception for all Account-related business logic errors.
 * Part of the FinVault Service Layer.
 */
public class AccountException extends BankException {

    public AccountException(String message) {
        super(message);
    }

    /**
     * Constructor to wrap lower-level causes while maintaining the banking context.
     */
    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }
}