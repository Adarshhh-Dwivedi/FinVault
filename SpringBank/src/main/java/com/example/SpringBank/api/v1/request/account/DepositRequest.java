package com.example.SpringBank.api.v1.request.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive; // New import for financial safety
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Modernized for SpringBank
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositRequest {

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Long accountNumber;

    @NotNull(message = "{constraints.NotEmpty.message}")
    @Positive(message = "Deposit amount must be greater than zero") // Critical for banking logic
    private BigDecimal depositAmt;
}