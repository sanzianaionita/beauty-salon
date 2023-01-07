package com.example.beautysalon.controller;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.service.AppointmentService;
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
@RequestMapping("/appointment")
@Api(tags = "Appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Fetch all appointments from a salon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all appointments from a salon",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/all-from-salon")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsFromSalon(@RequestParam UUID salonId) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentsFromSalon(salonId));
    }

    @Operation(summary = "Fetch all appointments of a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all appointments of a client",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/all-for-client")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsForClient(@RequestParam UUID clientId) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentsForClient(clientId));
    }

    @Operation(summary = "Fetch all appointments for an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all appointments for an employee",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/all-for-employee")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsForEmployee(@RequestParam UUID employeeId) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentsForEmployee(employeeId));
    }

    @Operation(summary = "Create an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Appointment created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @PostMapping("")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestParam UUID salonId,
                                            @RequestParam UUID serviceId,
                                            @RequestParam UUID employeeId,
                                            @RequestParam UUID clientId,
                                            @RequestParam String appointmentDate) {

        return ResponseEntity.ok(appointmentService.createAppointment(salonId, serviceId, employeeId, clientId, appointmentDate));

    }

    @Operation(summary = "Edit an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "New appointment",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @PutMapping("/edit-appointment")
    public ResponseEntity<AppointmentDTO> editAppointment(@RequestBody AppointmentDTO appointmentDTO) {

        return ResponseEntity.ok(appointmentService.editAppointment(appointmentDTO));
    }

    @Operation(summary = "Delete an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Appointment deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @DeleteMapping("/delete-appointment")
    public ResponseEntity<Integer> deleteAppointment(@RequestParam UUID appointmentId) {

        return ResponseEntity.ok(appointmentService.deleteAppointment(appointmentId));
    }
}
