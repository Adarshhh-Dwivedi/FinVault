package com.example.SpringBank.api.v1.request.account;

import com.example.SpringBank.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Modern Java Record implementation for SpringBank.
 * Records are immutable and concise.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateAccountRequest(

        @NotNull(message = "{constraints.NotEmpty.message}")
        Long customerId,

        @NotNull(message = "{constraints.NotEmpty.message}")
        Account.AccountType type,

        @NotNull(message = "{constraints.NotEmpty.message}")
        Long branchId,

        @NotNull(message = "{constraints.NotEmpty.message}")
        @DecimalMin(value = "0.0", inclusive = true, message = "Initial balance cannot be negative")
        BigDecimal balance
) {}