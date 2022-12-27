package com.sophos.bank.entity;

import jakarta.persistence.*;

@Entity
@Table(name="identificationType")
public class identificationType {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public int idType;

    private String type;

    public identificationType(){

    }

    public int getIdType() {
        return this.idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
