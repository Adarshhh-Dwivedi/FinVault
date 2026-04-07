package com.example.SpringBank.api.v1.request.account;

import com.example.SpringBank.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull; // UPDATED: Changed from javax to jakarta
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountRequest {

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Long customerId;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Account.AccountType type;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Long branchId;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private BigDecimal balance;

}