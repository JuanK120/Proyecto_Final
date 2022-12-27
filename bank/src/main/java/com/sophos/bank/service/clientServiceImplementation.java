package com.sophos.bank.service;


import com.sophos.bank.entity.client;
import com.sophos.bank.repository.clientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class clientServiceImplementation implements clientService{

    @Autowired
    clientRepository clientRepository;

    @Override
    public client createClient(client client) {

        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public client updateClient(client client) {
        clientRepository.setClientInfoById(
                client.getClientId(),client.getIdType(),
                client.getIdNumber(),client.getName(),
                client.getLastName(),client.getEmail(),
                client.getBirthDate(),client.getCreationDate(),
                client.getModificationDate(),client.getModificationUser(),
                client.isActive()
        );
        return client;
    }

    @Override
    public List<client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<client> getClientById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public boolean deleteClientById(int id) {
        return getClientById(id).map(client -> {
            clientRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
