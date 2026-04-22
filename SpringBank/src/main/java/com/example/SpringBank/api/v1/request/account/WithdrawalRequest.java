package com.example.SpringBank.api.v1.request.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive; // Crucial for banking integrity
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Modernized for SpringBank.
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalRequest {

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Long accountNumber;

    @NotNull(message = "{constraints.NotEmpty.message}")
    @Positive(message = "Withdrawal amount must be greater than zero")
    private BigDecimal withdrawalAmt; // Corrected spelling from 'withdrawl' to 'withdrawal'
}