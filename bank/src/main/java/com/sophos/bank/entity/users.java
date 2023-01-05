package com.sophos.bank.entity;


import jakarta.persistence.*;

@Entity
@Table(name="users")
public class users{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    private String email;

    private String password;

    private Boolean active;

    public users(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setUser(users user){
        this.userId=user.userId;
        this.userName=user.userName;
        this.email=user.email;
        this.password=user.password;
        this.active=user.active;
    }
}
