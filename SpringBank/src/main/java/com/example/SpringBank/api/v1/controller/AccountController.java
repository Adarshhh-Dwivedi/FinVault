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
import jakarta.validation.Valid; // UPDATED from javax to jakarta
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts != null && !accounts.isEmpty()) {
            return accounts.stream()
                    .map(account -> modelMapper.map(account, AccountDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @GetMapping("/{accountNumber}")
    public AccountDto getAccountByNumber(@PathVariable("accountNumber") Long accountNumber) throws EntityNotFoundException {
        Account account = accountService.getAccount(accountNumber);
        if (account != null) {
            return modelMapper.map(account, AccountDto.class);
        }
        return null;
    }

    @PostMapping("/create")
    public AccountDto createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) throws EntityException {
        AccountDto accountDto = modelMapper.map(createAccountRequest, AccountDto.class);
        Account account = accountService.createAccount(accountDto);
        if (account != null) {
            return modelMapper.map(account, AccountDto.class);
        }
        return null;
    }

    @PostMapping("/deposit")
    public AccountDto depositMoney(@RequestBody @Valid DepositRequest depositRequest) throws EntityNotFoundException {
        // Explicit mapping to handle specific field 'depositAmt'
        AccountDto accountDto = new AccountDto().setAccountNumber(depositRequest.getAccountNumber());
        Account account = accountService.creditAmount(accountDto, depositRequest.getDepositAmt());
        if (account != null) {
            return modelMapper.map(account, AccountDto.class);
        }
        return null;
    }

    @PostMapping("/withdraw")
    public AccountDto withdrawMoney(@RequestBody @Valid WithdrawalRequest withdrawalRequest) throws EntityNotFoundException, InsufficientFundsException {
        AccountDto accountDto = new AccountDto().setAccountNumber(withdrawalRequest.getAccountNumber());
        Account account = accountService.debitAmount(accountDto, withdrawalRequest.getWithdrawlAmt());
        if (account != null) {
            return modelMapper.map(account, AccountDto.class);
        }
        return null;
    }

    @PostMapping("/transfer")
    public List<AccountDto> transferMoney(@RequestBody @Valid TransferFundRequest transferFundRequest) throws InsufficientFundsException, EntityNotFoundException {
        AccountDto debitAccountDto = new AccountDto().setAccountNumber(transferFundRequest.getDebitAccountNumber());
        AccountDto creditAccountDto = new AccountDto().setAccountNumber(transferFundRequest.getCreditAccountNumber());
        List<Account> accounts = accountService.transferFunds(debitAccountDto, creditAccountDto, transferFundRequest.getAmount());
        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDto.class))
                .collect(Collectors.toList());
    }
}