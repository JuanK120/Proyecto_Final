package com.sophos.bank.service;

import com.sophos.bank.entity.transaction;

import java.util.List;
import java.util.Optional;

public interface transactionService {
    public transaction createTransaction(transaction transaction);
    public List<transaction> getAllTransactions();
    public Optional<transaction> getTransactionById(int id);
    public boolean deleteTransactionById(int id);
}
