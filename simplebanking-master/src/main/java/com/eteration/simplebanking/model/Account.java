package com.eteration.simplebanking.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private Long id;
    private String owner;
    private String accountNumber;
    private double balance;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public Account() {

    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) throws InsufficientBalanceException {
        if (this.balance >= amount) {
            this.balance -= amount;
        } else {
            throw new InsufficientBalanceException("Yetersiz bakiye.");
        }
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (this.balance >= amount) {
            this.balance -= amount;
        } else {
            throw new InsufficientBalanceException("Yetersiz bakiye.");
        }
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        if (transaction instanceof DepositTransaction) {
            this.deposit(transaction.getAmount());
        } else if (transaction instanceof WithdrawalTransaction) {
            this.withdraw(transaction.getAmount());
        } else {
            throw new IllegalArgumentException("Geçersiz işlem türü: " + transaction.getClass());
        }

        this.transactions.add(transaction);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
