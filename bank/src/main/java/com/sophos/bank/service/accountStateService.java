package com.sophos.bank.service;

import com.sophos.bank.entity.accountState;

import java.util.List;
import java.util.Optional;

public interface accountStateService {

    public accountState createAccountState(accountState accountState);
    public List<accountState> getAllAccountStates();
    public Optional<accountState> getAccountStateById(int id);
    public boolean deleteAccountStateById(int id);
}
