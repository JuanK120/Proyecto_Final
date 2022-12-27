package com.sophos.bank.service;

import com.sophos.bank.entity.accountState;
import com.sophos.bank.repository.accountStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class accountStateServiceImplementation implements accountStateService{

    @Autowired
    accountStateRepository accountStateRepository;

    @Override
    public accountState createAccountState(accountState accountState) {
        return accountStateRepository.save(accountState);
    }

    @Override
    public List<accountState> getAllAccountStates() {
        return accountStateRepository.findAll();
    }

    @Override
    public Optional<accountState> getAccountStateById(int id) {
        return accountStateRepository.findById(id);
    }

    @Override
    public boolean deleteAccountStateById(int id) {
        return getAccountStateById(id).map(accountState -> {
            accountStateRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
