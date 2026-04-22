package com.example.SpringBank.dto;

import com.example.SpringBank.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Modernized for SpringBank.
 * Represents the unified view of an Account for the API layer.
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {

    private Long accountNumber;

    private Long customerId;

    private Long branchId;

    private Account.AccountType type;

    private BigDecimal balance;

    // Using a nested DTO allows for detailed owner info if requested,
    // while customerId provides a quick reference.
    private CustomerDto accountOwner;
}