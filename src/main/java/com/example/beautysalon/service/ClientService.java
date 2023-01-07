package com.example.beautysalon.service;

import com.example.beautysalon.customExceptions.CustomException;
import com.example.beautysalon.dto.ClientDTO;
import com.example.beautysalon.mappers.ClientMapper;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.repository.ClientRepository;
import com.example.beautysalon.security.util.SecurityUtils;
import org.springframework.http.HttpStatus;
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
            throw new CustomException("Client does not exist", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        UUID userId = SecurityUtils.getUserId();

        if (userId == null) {
            throw new CustomException("User not logged in!", HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
        }

        clientRepository.findByUserId(userId).ifPresent(client -> {
            if (!byId.get().getId().equals(client.getId())) {
                throw new CustomException("Can't change the details of another account.", HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
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