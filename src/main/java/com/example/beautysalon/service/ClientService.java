package com.example.beautysalon.service;

import com.example.beautysalon.dto.ClientDTO;
import com.example.beautysalon.mappers.ClientMapper;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDTO findById(UUID id) {
        Optional<Client> byId = clientRepository.findById(id);
        return byId.map(clientMapper::clientToClientDto).orElse(null);
    }
}