package com.example.SpringBank.api.v1.request.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive; // Added for financial integrity
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Refactored for SpringBank.
 * Handles the transfer of funds between two accounts.
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferFundRequest {

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Long debitAccountNumber;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Long creditAccountNumber;

    @NotNull(message = "{constraints.NotEmpty.message}")
    @Positive(message = "Transfer amount must be greater than zero")
    private BigDecimal amount;

    /**
     * Logic check: Ensure accounts are different.
     * While this can be done in the Service, it's good to keep in mind.
     */
    public boolean isSelfTransfer() {
        return debitAccountNumber != null && debitAccountNumber.equals(creditAccountNumber);
    }
}