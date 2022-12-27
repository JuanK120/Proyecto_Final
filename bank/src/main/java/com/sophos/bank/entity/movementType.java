package com.sophos.bank.entity;


import jakarta.persistence.*;

@Entity
@Table(name="movementType")
public class movementType {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idType;

    private String transactionType;

    public movementType() {
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
