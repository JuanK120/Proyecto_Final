package com.sophos.bank.service;

import com.sophos.bank.entity.transaction;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface transactionService {
    public transaction createTransaction(transaction transaction);
    public List<transaction> getAllTransactions();
    public List<transaction> getAllTransactionsByAccount(BigInteger targetProduct);
    public Optional<transaction> getTransactionById(int id);
    public boolean deleteTransactionById(int id);
}
