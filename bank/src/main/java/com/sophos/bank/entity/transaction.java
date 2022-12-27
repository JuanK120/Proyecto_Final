package com.sophos.bank.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="transaction")
public class transaction {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne(targetEntity = transactionType.class,cascade = CascadeType.ALL)
    private int transactionType;

    @ManyToOne(targetEntity = movementType.class,cascade = CascadeType.ALL)
    private int movementType;

    @ManyToOne(targetEntity = product.class,cascade = CascadeType.ALL)
    private int targetProduct;

    private Date movementDate;

    private String description;

    private int amount;

    private int balance;

    private int availableBalance;

    public transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public int getMovementType() {
        return movementType;
    }

    public void setMovementType(int movementType) {
        this.movementType = movementType;
    }

    public int getTargetProduct() {
        return targetProduct;
    }

    public void setTargetProduct(int targetProduct) {
        this.targetProduct = targetProduct;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }
}
