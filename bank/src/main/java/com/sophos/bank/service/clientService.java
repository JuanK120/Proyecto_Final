package com.sophos.bank.service;

import com.sophos.bank.entity.client;
import java.util.List;
import java.util.Optional;

public interface clientService {
    public client createClient(client client);
    public client updateClient(client client);
    public List<client> getAllClients();
    public Optional<client> getClientById(int id);
    public boolean deleteClientById(int id);
}
