package com.example.SpringBank.domain.model;

import com.example.SpringBank.service.account.exception.InsufficientFundsException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

/**
 * Modernized for SpringBank
 */
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "accounts") // Good practice to pluralize table names
public class Account extends BaseDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false) // Use snake_case for DB columns
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch coreBranch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer accountOwner;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO; // Default to zero to avoid NPE

    @Enumerated(EnumType.STRING) // CRITICAL: Save as "SAVINGS" instead of 0
    @Column(nullable = false)
    private AccountType type;

    /**
     * Debits the given amount from current account balance.
     */
    public void debit(BigDecimal debitAmount) throws InsufficientFundsException {
        if (this.balance == null) this.balance = BigDecimal.ZERO;

        if (this.balance.compareTo(debitAmount) >= 0) {
            this.balance = this.balance.subtract(debitAmount);
            return;
        }
        throw new InsufficientFundsException(this, debitAmount);
    }

    /**
     * Credits the given amount to current account balance
     */
    public void credit(BigDecimal creditAmount) {
        if (this.balance == null) this.balance = BigDecimal.ZERO;
        this.balance = this.balance.add(creditAmount);
    }

    public enum AccountType {
        SAVINGS, CURRENT, LOAN
    }
}