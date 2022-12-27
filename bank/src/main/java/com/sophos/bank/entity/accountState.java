package com.sophos.bank.entity;


import jakarta.persistence.*;

@Entity
@Table(name="accountState")
public class accountState {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idState;

    private String stateName;

    public accountState(){

    }

    public int getIdState() {
        return idState;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
