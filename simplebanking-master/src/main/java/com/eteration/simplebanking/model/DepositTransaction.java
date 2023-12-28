package com.eteration.simplebanking.model;

public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
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
