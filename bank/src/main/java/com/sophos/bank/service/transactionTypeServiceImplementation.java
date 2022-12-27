package com.sophos.bank.service;

import com.sophos.bank.entity.transactionType;
import com.sophos.bank.repository.transactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class transactionTypeServiceImplementation implements  transactionTypeService{

    @Autowired
    transactionTypeRepository transactionTypeRepository;

    @Override
    public transactionType createTransactionType(transactionType transactionType) {
        return transactionTypeRepository.save(transactionType);
    }

    @Override
    public List<transactionType> getAllTransactionType() {
        return transactionTypeRepository.findAll();
    }

    @Override
    public Optional<transactionType> getTransactionTypeById(int id) {
        return transactionTypeRepository.findById(id);
    }

    @Override
    public boolean deleteTransactionType(int id) {
        return getTransactionTypeById(id).map(transactionType -> {
            transactionTypeRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
