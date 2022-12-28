package com.sophos.bank.entity;



import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name="client")
public class client {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int clientId;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = identificationType.class,cascade = CascadeType.ALL)
    @JoinColumn(name="idType")
    private identificationType identificationType;

    private int idNumber;

    private String name;

    private String lastName;

    private String email;

    private Date birthDate;

    private Date creationDate;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "creationUser")
    private users cUsers;

    private Date modificationDate;

    @ManyToOne(cascade = CascadeType.MERGE)//(targetEntity = users.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "modificationUser")
    private users users;

    private boolean active;

    public client() {
    }

    public int getClientId() {
        return clientId;
    }

    public identificationType getIdType() {
        return identificationType;
    }

    public void setIdType(identificationType idType) {
        this.identificationType = idType;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public users getCreationUser() {
        return users;
    }

    public void setCreationUser(users modificationUser) {
        this.users = modificationUser;
    }

    public com.sophos.bank.entity.users getModificationUser() {
        return users;
    }

    public void setModificationUser(users modificationUser) {
        this.users = modificationUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
