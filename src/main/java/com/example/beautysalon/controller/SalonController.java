package com.example.beautysalon.controller;


import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.service.SalonService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/salon")
@Api(tags = "Salons")
public class SalonController {
    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }


    @Operation(summary = "Fetch all salons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all salons",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'EMPLOYEE')")
    @GetMapping("")
    public ResponseEntity<List<SalonDTO>> getSalons() {
        return ResponseEntity.ok(salonService.getSalons());
    }


    @Operation(summary = "Fetch all salons by specific location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all salons from a specific location",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Cannot parse id",
                    content = @Content)
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'EMPLOYEE')")
    @GetMapping("/by-location")
    public ResponseEntity<List<SalonDTO>> getSalonsByLocation(@RequestParam UUID locationId) {
        return ResponseEntity.ok(salonService.getSalonsByLocation(locationId));
    }

    @Operation(summary = "Fetch all salons by specific name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all salons for a specific name",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content)
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'EMPLOYEE')")
    @GetMapping("/by-name")
    public ResponseEntity<SalonDTO> getSalonsBySalonName(@RequestParam String salonName) {
        return ResponseEntity.ok(salonService.getSalonsBySalonName(salonName));
    }

    @Operation(summary = "Create salon as admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Created salon",
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<SalonDTO> createSalon(@RequestBody Salon salon) {

        return new ResponseEntity<>(salonService.createSalon(salon), HttpStatus.CREATED);
    }

}
