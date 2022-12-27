package com.sophos.bank.service;

import com.sophos.bank.entity.users;

import java.util.List;
import java.util.Optional;

public interface usersService {
    public users createUser(users user);
    public List<users> getAllUsers();
    public Optional<users> getUserById(int id);
    public boolean deleteUserById(int id);
}
