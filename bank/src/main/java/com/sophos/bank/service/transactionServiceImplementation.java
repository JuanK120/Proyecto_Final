package com.sophos.bank.service;

import com.sophos.bank.entity.transaction;
import com.sophos.bank.repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class transactionServiceImplementation implements transactionService{

    @Autowired
    transactionRepository transactionRepository;

    @Override
    public transaction createTransaction(transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<transaction> getAllTransactionsByAccount(long targetProduct) {
        return transactionRepository.findAllByTargetProduct(targetProduct);
    }

    @Override
    public Optional<transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    @Override
    public boolean deleteTransactionById(int id) {
        return getTransactionById(id).map(transaction -> {
            transactionRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
