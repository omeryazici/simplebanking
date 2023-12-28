package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/credit")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountId, @RequestBody DepositTransaction depositTransaction) {
        try {
            Account account = accountService.findAccount(accountId);

            double amount = depositTransaction.getAmount();
            account.credit(amount);

            TransactionStatus transactionStatus = new TransactionStatus("OK");

            return ResponseEntity.ok(transactionStatus);

        } catch (AccountNotFoundException ex) {
            TransactionStatus errorStatus = new TransactionStatus("ERROR");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorStatus);
        } catch (Exception ex) {
            TransactionStatus errorStatus = new TransactionStatus("ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorStatus);
        }
    }

    @PostMapping("/{accountId}/debit")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountId, @RequestBody WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        try {
            Account account = accountService.findAccount(accountId);

            double amount = withdrawalTransaction.getAmount();
            account.debit(amount);

            TransactionStatus transactionStatus = new TransactionStatus("OK");

            return ResponseEntity.ok(transactionStatus);

        } catch (AccountNotFoundException ex) {
            TransactionStatus errorStatus = new TransactionStatus("ERROR");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorStatus);
        }
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {
        try {
            Account account = accountService.findAccount(accountId);
            return ResponseEntity.ok(account);
        } catch (AccountNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}