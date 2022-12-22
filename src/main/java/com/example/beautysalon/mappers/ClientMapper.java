package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.ClientDTO;
import com.example.beautysalon.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO clientToClientDto (Client client);

    List<ClientDTO> clientToClientDto (List<Client> clients);

    Client clientDtoToClient (ClientDTO clientDTO);

    List<Client> clientDtoToClient (List<ClientDTO> clientDTOS);

}