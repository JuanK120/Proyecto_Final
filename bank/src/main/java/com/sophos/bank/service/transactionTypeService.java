package com.sophos.bank.service;

import com.sophos.bank.entity.transactionType;

import java.util.List;
import java.util.Optional;

public interface transactionTypeService {
    public transactionType createTransactionType(transactionType transactionType);
    public List<transactionType> getAllTransactionType();
    public Optional<transactionType> getTransactionTypeById(int id);
    public boolean deleteTransactionType(int id);
}
