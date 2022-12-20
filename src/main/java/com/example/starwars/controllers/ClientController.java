package com.example.starwars.controllers;

import com.example.starwars.model.Client;
import com.example.starwars.repository.ClientRepository;
import com.example.starwars.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clients")

public class ClientController {
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> list() {return clientRepository.findAll();}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping //---------------------------------------------------------------------------------------------------
    public Client add(@RequestBody Client client) {
        return clientService.add(client);
    }
}
