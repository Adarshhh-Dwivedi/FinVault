package com.example.SpringBank.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * Modernized for SpringBank
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor // Added for JPA compliance
@Entity
@Table(name = "customers")
public class Customer extends BaseDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long customerId;

    @Column(unique = true, nullable = false)
    private String ssn;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "accountOwner")
    private Set<Account> accounts = new HashSet<>(); // Initialize to avoid NPE

    @Column(nullable = false)
    private String name;

    private String address1;
    private String address2;
    private String city;
    private String contactNumber;

    /**
     * Helper method to keep bidirectional relationship in sync
     */
    public void addAccount(Account account) {
        if (accounts == null) {
            accounts = new HashSet<>();
        }
        accounts.add(account);
        account.setAccountOwner(this);
    }
}