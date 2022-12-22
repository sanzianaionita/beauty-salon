package com.example.beautysalon.controller;

import com.example.beautysalon.dto.EmployeeDTO;
import com.example.beautysalon.dto.PositionDTO;
import com.example.beautysalon.dto.ServiceDTO;
import com.example.beautysalon.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("position")
@Api(tags = "Position")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @Operation(summary = "Fetch all positions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all positions",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'EMPLOYEE')")
    @GetMapping("/all")
    public ResponseEntity<List<PositionDTO>> getAllPositions () {

        List<PositionDTO> allPositions = positionService.getAllPositions();
        return ResponseEntity.ok(allPositions);
    }
}
