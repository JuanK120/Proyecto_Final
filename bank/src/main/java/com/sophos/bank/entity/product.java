package com.sophos.bank.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="product")
public class product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToOne(targetEntity = client.class,cascade = CascadeType.ALL)
    private int owner;

    @ManyToOne(targetEntity = productType.class,cascade = CascadeType.ALL)
    private int productType;

    private int productNumber;

    @ManyToOne(targetEntity = accountState.class,cascade = CascadeType.ALL)
    private int state;

    private int balance;

    private int availableBalance;

    private boolean gmfExempt;

    private Date creationDate;

    @ManyToOne(targetEntity = users.class)
    private int crationUser;

    private Date modificationDate;

    @ManyToOne(targetEntity = users.class)
    private int modificationUser;

    public product() {
    }

    public int getProductId() {
        return productId;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public boolean isGmfExempt() {
        return gmfExempt;
    }

    public void setGmfExempt(boolean gmfExempt) {
        this.gmfExempt = gmfExempt;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getCrationUser() {
        return crationUser;
    }

    public void setCrationUser(int crationUser) {
        this.crationUser = crationUser;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(int modificationUser) {
        this.modificationUser = modificationUser;
    }
}
