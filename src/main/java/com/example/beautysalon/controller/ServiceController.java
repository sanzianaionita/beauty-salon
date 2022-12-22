package com.example.beautysalon.controller;

import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.dto.ServiceDTO;
import com.example.beautysalon.model.Service;
import com.example.beautysalon.service.ServiceClassForServices;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/service")
@Api(tags = "Services")
public class ServiceController {

    private final ServiceClassForServices serviceClassForServices;

    public ServiceController(ServiceClassForServices serviceClassForServices) {
        this.serviceClassForServices = serviceClassForServices;
    }

    @Operation(summary = "Fetch all services by salon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all services by salon",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'EMPLOYEE')")
    @GetMapping("/by-salon")
    public Map<String, List<ServiceDTO>> getServicesBySalon(@RequestParam String salonName) {
        return serviceClassForServices.getServicesBySalon(salonName);
    }
}
