package com.example.starwars.services;

import com.example.starwars.model.Client;
import com.example.starwars.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client add(Client client) {
        return clientRepository.save(client);
    }
}

