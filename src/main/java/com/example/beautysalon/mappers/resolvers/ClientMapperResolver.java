package com.example.beautysalon.mappers.resolvers;

import com.example.beautysalon.model.Client;
import com.example.beautysalon.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClientMapperResolver {

    private final ClientRepository clientRepository;

    public ClientMapperResolver(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(UUID id) {
        Optional<Client> byId = clientRepository.findById(id);
        return byId.orElse(null);
    }
}