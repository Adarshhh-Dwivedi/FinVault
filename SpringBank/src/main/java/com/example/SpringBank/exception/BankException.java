package com.example.SpringBank.exception;

/**
 * Modernized Base Exception for SpringBank.
 * All custom business-logic exceptions should extend this.
 */
public class BankException extends Exception {

    public BankException(String message) {
        super(message);
    }

    /**
     * Allows wrapping another exception while keeping the original cause.
     */
    public BankException(String message, Throwable cause) {
        super(message, cause);
    }
}