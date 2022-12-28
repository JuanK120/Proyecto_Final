package com.sophos.bank.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="transaction")
public class transaction {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionType")
    private transactionType transactionType;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "movementType")
    private movementType movementType;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "targetProduct")
    private product targetProduct;

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

    public transactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(transactionType transactionType) {
        this.transactionType = transactionType;
    }

    public movementType getMovementType() {
        return movementType;
    }

    public void setMovementType(movementType movementType) {
        this.movementType = movementType;
    }

    public product getTargetProduct() {
        return targetProduct;
    }

    public void setTargetProduct(product targetProduct) {
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
