package com.example.FinVault.service.account;

import com.example.FinVault.domain.model.Account;
import com.example.FinVault.dto.AccountDto;
import com.example.FinVault.exception.EntityNotFoundException;
import com.example.FinVault.service.account.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface defining the core banking operations for FinVault.
 */
public interface AccountService {

    /**
     * Opens a new bank account.
     */
    Account createAccount(AccountDto accountDto);

    /**
     * Retrieves all registered accounts.
     */
    List<Account> getAllAccounts();

    /**
     * Finds a specific account by its unique ID or Account Number.
     */
    Account getAccount(Long id) throws EntityNotFoundException;

    /**
     * Deposits money into an account.
     */
    Account creditAmount(AccountDto accountDto, BigDecimal depositAmt) throws EntityNotFoundException;

    /**
     * Withdraws money from an account, ensuring balance integrity.
     */
    Account debitAmount(AccountDto accountDto, BigDecimal withdrawalAmt)
            throws EntityNotFoundException, InsufficientFundsException;

    /**
     * Performs a secure transfer between two accounts.
     * Returns a list containing both the sender and receiver updated accounts.
     */
    List<Account> transferFunds(AccountDto fromAccountDto, AccountDto toAccountDto, BigDecimal amount)
            throws EntityNotFoundException, InsufficientFundsException;
}