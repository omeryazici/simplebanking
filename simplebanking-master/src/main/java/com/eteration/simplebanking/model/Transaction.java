package com.eteration.simplebanking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public abstract class Transaction {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    protected Date date;
    protected double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction(double amount) {
        this.date = new Date();
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public abstract String toString();
}
