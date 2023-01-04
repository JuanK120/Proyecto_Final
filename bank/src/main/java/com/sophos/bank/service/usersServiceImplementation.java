package com.sophos.bank.service;

import com.sophos.bank.entity.users;
import com.sophos.bank.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usersServiceImplementation implements usersService {

    @Autowired
    usersRepository usersRepository;

    @Override
    public users createUser(users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<users> getUserById(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public Optional<users> getUserByEmailAndPassword(String email, String password) {
        return usersRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public boolean deleteUserById(int id) {
        return getUserById(id).map(users -> {
            usersRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
