package com.example.beautysalon.service;

import com.example.beautysalon.dto.ClientDTO;
import com.example.beautysalon.mappers.ClientMapperImpl;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.repository.ClientRepository;
import com.example.beautysalon.security.util.SecurityUtils;
import com.example.beautysalon.utils.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    private static ClientRepository clientRepository;
    private static ClientService clientService;
    private static ClientMapperImpl clientMapper;
    private static MockedStatic<SecurityUtils> securityUtilsMockedStatic;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void setup() {
        clientRepository = mock(ClientRepository.class);
        clientMapper = mock(ClientMapperImpl.class);
        clientService = new ClientService(clientRepository, clientMapper);
        securityUtilsMockedStatic = mockStatic(SecurityUtils.class);
    }

    @AfterAll
    public static void close() {
        securityUtilsMockedStatic.close();
    }

    @Test
    public void findById() {
        when(clientRepository.findById(any())).thenReturn(Optional.of(Utils.createClient()));
        when(clientMapper.clientToClientDto(any(Client.class))).thenReturn(Utils.returnClientDto());

        ClientDTO byId = clientService.findById(any());

        assertNotNull(byId);
    }

    @Test
    public void editDetailsOfClient(){
        Client client = Utils.createClient();
        ClientDTO dto = Utils.returnClientDto();

        dto.setId(client.getId());

        when(clientRepository.findById(any())).thenReturn(Optional.of(Utils.createClient()));
        when(clientRepository.findByUserId(dto.getId())).thenReturn(Optional.of(client));
        securityUtilsMockedStatic.when(SecurityUtils::getUserId).thenReturn(UUID.randomUUID());
        when(clientMapper.clientDtoToClient(any(ClientDTO.class))).thenReturn(client);
        when(clientRepository.save(any())).thenReturn(Utils.createClient());
        when(clientMapper.clientToClientDto(any(Client.class))).thenReturn(dto);

        ClientDTO clientDTO = clientService.editDetailsOfClient(client.getFirstName(), client.getLastName(), client.getPhoneNumber(), UUID.randomUUID());

        assertNotNull(clientDTO);
    }
}
