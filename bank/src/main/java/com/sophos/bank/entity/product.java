package com.sophos.bank.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="product")
public class product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private client owner;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "productType")
    private productType productType;

    private long productNumber;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "state")
    private accountState state;

    private int balance;

    private int availableBalance;

    private boolean gmfExempt;

    private Date creationDate;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "crationUser")
    private users crationUser;

    private Date modificationDate;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "modificationUser")
    private users modificationUser;

    public product() {
    }

    public int getProductId() {
        return productId;
    }

    public client getOwner() {
        return owner;
    }

    public void setOwner(client owner) {
        this.owner = owner;
    }

    public productType getProductType() {
        return productType;
    }

    public void setProductType(productType productType) {
        this.productType = productType;
    }

    public long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(long productNumber) {
        this.productNumber = productNumber;
    }

    public accountState getState() {
        return state;
    }

    public void setState(accountState state) {
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

    public users getCrationUser() {
        return crationUser;
    }

    public void setCrationUser(users crationUser) {
        this.crationUser = crationUser;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public users getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(users modificationUser) {
        this.modificationUser = modificationUser;
    }
}
