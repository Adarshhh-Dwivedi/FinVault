package com.example.FinVault.service.account;

import com.example.FinVault.domain.model.Account;
import com.example.FinVault.domain.model.Branch;
import com.example.FinVault.domain.model.Customer;
import com.example.FinVault.domain.repository.AccountRepository;
import com.example.FinVault.domain.repository.BranchRepository;
import com.example.FinVault.domain.repository.CustomerRepository;
import com.example.FinVault.dto.AccountDto;
import com.example.FinVault.exception.EntityNotFoundException;
import com.example.FinVault.service.account.exception.InsufficientFundsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of banking operations for FinVault.
 * Optimized for Java 21 and Spring Boot 3.4.
 */
@Service
@RequiredArgsConstructor // Replaces @Autowired for cleaner Constructor Injection
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper mapper;

    @Override
    public List<Account> getAllAccounts() {
        // Modernized: toList() is a direct method in Java 16+
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long accountNumber) {
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException(Account.class, "accountNumber", accountNumber.toString()));
    }

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto) {
        Customer customer = customerRepository.findById(accountDto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, "customerId", accountDto.getCustomerId().toString()));

        Branch branch = branchRepository.findById(accountDto.getBranchId())
                .orElseThrow(() -> new EntityNotFoundException(Branch.class, "branchId", accountDto.getBranchId().toString()));

        Account account = mapper.map(accountDto, Account.class);
        account.setCoreBranch(branch);
        account.setAccountOwner(customer);
        return accountRepository.save(account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Account creditAmount(AccountDto accountDto, BigDecimal creditAmt) {
        if (creditAmt.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");

        Account account = getAccount(accountDto.getAccountNumber());
        account.credit(creditAmt);
        return accountRepository.save(account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {InsufficientFundsException.class}, isolation = Isolation.READ_COMMITTED)
    public Account debitAmount(AccountDto accountDto, BigDecimal debitAmt) {
        if (debitAmt.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");

        Account account = getAccount(accountDto.getAccountNumber());
        account.debit(debitAmt); // Internal logic in Account model should check balance
        return accountRepository.save(account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, isolation = Isolation.READ_COMMITTED)
    public List<Account> transferFunds(AccountDto debitAccountDto, AccountDto creditAccountDto, BigDecimal amount) {
        Account debitAccount = debitAmount(debitAccountDto, amount);
        Account creditAccount = creditAmount(creditAccountDto, amount);
        return List.of(debitAccount, creditAccount);
    }
}