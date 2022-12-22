package com.example.beautysalon.controller;

import com.example.beautysalon.dto.LocationDTO;
import com.example.beautysalon.dto.PositionDTO;
import com.example.beautysalon.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
@Api(tags = "Locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Fetch all locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all locations",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'EMPLOYEE')")
    @GetMapping("/all")
    public ResponseEntity<List<LocationDTO>> getAllLocations () {

        List<LocationDTO> allLocations = locationService.getAllLocations();
        return ResponseEntity.ok(allLocations);
    }
}
