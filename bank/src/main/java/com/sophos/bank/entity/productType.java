package com.sophos.bank.entity;


import jakarta.persistence.*;

@Entity
@Table(name="productType")
public class productType {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idType;

    private String accountType;

    public productType(){

    }

    public int getIdType() {
        return idType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
