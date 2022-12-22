package com.example.beautysalon.service;

import com.example.beautysalon.dto.ClientDTO;
import com.example.beautysalon.mappers.ClientMapper;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.repository.ClientRepository;
import com.example.beautysalon.security.util.SecurityUtils;
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

    public ClientDTO editDetailsOfClient(String firstName, String lastName, String phoneNumber, UUID clientId){

        Optional<Client> byId = clientRepository.findById(clientId);

        if (byId.isEmpty()) {
            throw new RuntimeException("Client does not exist");
        }

        UUID userId = SecurityUtils.getUserId();

        if (userId == null) {
            throw new RuntimeException("User not logged in!");
        }

        clientRepository.findByUserId(userId).ifPresent(client -> {
            if (!byId.get().getId().equals(client.getId())) {
                throw new RuntimeException("Client details not visible.");
            }
        });

        Client client = byId.get();

        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhoneNumber(phoneNumber);

        Client savedClient = clientRepository.save(byId.get());

        return clientMapper.clientToClientDto(savedClient);
    }
}