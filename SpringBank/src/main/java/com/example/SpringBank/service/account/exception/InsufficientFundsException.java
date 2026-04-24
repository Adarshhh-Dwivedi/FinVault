package com.example.SpringBank.service.account.exception;

import com.example.FinVault.domain.model.Account;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class InsufficientFundsException extends AccountException {

    public InsufficientFundsException(Account account, BigDecimal requested) {
        super(String.format("Transaction Denied: Account [%s] has insufficient funds. Available: %s | Requested: %s",
                account.getAccountNumber(),
                formatCurrency(account.getBalance()),
                formatCurrency(requested)));
    }

    private static String formatCurrency(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(amount);
    }
}