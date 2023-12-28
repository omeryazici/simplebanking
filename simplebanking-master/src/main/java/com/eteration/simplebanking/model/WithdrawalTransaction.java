package com.eteration.simplebanking.model;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "DepositTransactionDto{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}


