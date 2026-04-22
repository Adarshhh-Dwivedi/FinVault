package com.example.SpringBank.api.v1.controller;

import com.example.SpringBank.api.v1.request.account.CreateAccountRequest;
import com.example.SpringBank.api.v1.request.account.DepositRequest;
import com.example.SpringBank.api.v1.request.account.TransferFundRequest;
import com.example.SpringBank.api.v1.request.account.WithdrawalRequest;
import com.example.SpringBank.domain.model.Account;
import com.example.SpringBank.dto.AccountDto;
import com.example.SpringBank.exception.EntityException;
import com.example.SpringBank.exception.EntityNotFoundException;
import com.example.SpringBank.service.account.AccountService;
import com.example.SpringBank.service.account.exception.InsufficientFundsException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    // Using final fields for better thread safety and immutability
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    // Constructor injection is the standard for Spring Boot 3+
    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public List<AccountDto> getAccounts() {
        return accountService.getAllAccounts()
                .stream()
                .map(account -> modelMapper.map(account, AccountDto.class))
                .toList(); // Cleaner Java 21 syntax
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountByNumber(@PathVariable("accountNumber") Long accountNumber) throws EntityNotFoundException {
        Account account = accountService.getAccount(accountNumber);
        return ResponseEntity.ok(modelMapper.map(account, AccountDto.class));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED) // Explicitly return 201 for creation
    public AccountDto createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) throws EntityException {
        AccountDto accountDto = modelMapper.map(createAccountRequest, AccountDto.class);
        Account account = accountService.createAccount(accountDto);
        return modelMapper.map(account, AccountDto.class);
    }

    @PostMapping("/deposit")
    public AccountDto depositMoney(@RequestBody @Valid DepositRequest depositRequest) throws EntityNotFoundException {
        AccountDto accountDto = new AccountDto().setAccountNumber(depositRequest.getAccountNumber());
        Account account = accountService.creditAmount(accountDto, depositRequest.getDepositAmt());
        return modelMapper.map(account, AccountDto.class);
    }

    @PostMapping("/withdraw")
    public AccountDto withdrawMoney(@RequestBody @Valid WithdrawalRequest withdrawalRequest) throws EntityNotFoundException, InsufficientFundsException {
        AccountDto accountDto = new AccountDto().setAccountNumber(withdrawalRequest.getAccountNumber());
        Account account = accountService.debitAmount(accountDto, withdrawalRequest.getWithdrawlAmt());
        return modelMapper.map(account, AccountDto.class);
    }

    @PostMapping("/transfer")
    public List<AccountDto> transferMoney(@RequestBody @Valid TransferFundRequest transferFundRequest) throws InsufficientFundsException, EntityNotFoundException {
        AccountDto debitAccountDto = new AccountDto().setAccountNumber(transferFundRequest.getDebitAccountNumber());
        AccountDto creditAccountDto = new AccountDto().setAccountNumber(transferFundRequest.getCreditAccountNumber());

        List<Account> accounts = accountService.transferFunds(debitAccountDto, creditAccountDto, transferFundRequest.getAmount());

        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDto.class))
                .toList();
    }
}