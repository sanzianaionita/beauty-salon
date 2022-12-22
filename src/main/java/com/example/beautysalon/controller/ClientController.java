package com.example.beautysalon.controller;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.dto.ClientDTO;
import com.example.beautysalon.service.AppointmentService;
import com.example.beautysalon.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@Api(tags = "Clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Edit client details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Client details changed",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Bad request, adjust before retrying",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content)
    })
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @PutMapping("/edit")
    public ResponseEntity<ClientDTO> editDetailsOfClient(@RequestParam String firstName,
                                                         @RequestParam String lastName,
                                                         @RequestParam UUID clientId,
                                                         @RequestParam String phoneNumber) {

        return ResponseEntity.ok(clientService.editDetailsOfClient(firstName, lastName, phoneNumber, clientId));
    }
}
