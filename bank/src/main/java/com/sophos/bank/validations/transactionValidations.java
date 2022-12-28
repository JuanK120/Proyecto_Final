package com.sophos.bank.validations;

public class transactionValidations {

    public boolean transactionIsViable(int balance, int amount) {
        return balance>=amount;
    }
}
