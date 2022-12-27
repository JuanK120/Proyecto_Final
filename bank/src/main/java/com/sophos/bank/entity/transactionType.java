package com.sophos.bank.entity;


import jakarta.persistence.*;

@Entity
@Table(name="transactionType")
public class transactionType {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idType;

    private String transactionType;

    public transactionType() {
    }

    public int getIdType() {
        return idType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
