package com.example.beautysalon.controller;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.dto.EmployeeDTO;
import com.example.beautysalon.service.AppointmentService;
import com.example.beautysalon.service.EmployeeService;
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
@RequestMapping("/employee")
@Api(tags = "Employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AppointmentService appointmentService;

    public EmployeeController(EmployeeService employeeService, AppointmentService appointmentService) {
        this.employeeService = employeeService;
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Fetch all employees by function")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all employees by function",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @GetMapping("/allByFunction")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByPosition(@RequestParam String functionName) {

        List<EmployeeDTO> employeesByPosition = employeeService.getEmployeesByPosition(functionName);

        return ResponseEntity.ok(employeesByPosition);
    }

    @Operation(summary = "Fetch all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all employees",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
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
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsForEmployee(@RequestParam UUID employeeId) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentsForEmployee(employeeId));
    }

    @Operation(summary = "Confirm an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Appointment confirmed/unconfirmed",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Not authorized",
                    content = @Content),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/confirm-appointment")
    public ResponseEntity<AppointmentDTO> confirmAppointment(@RequestParam UUID appointmentId,
                                             @RequestParam boolean confirmed) {

        return ResponseEntity.ok(employeeService.confirmAppointment(appointmentId, confirmed));
    }

    @Operation(summary = "Edit employee details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Employee detaile changed",
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
    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @PutMapping("/edit")
    public ResponseEntity<EmployeeDTO> editDetailsOfEmployee(@RequestParam String firstName,
                                                             @RequestParam String lastName,
                                                             @RequestParam UUID employeeId) {

        return ResponseEntity.ok(employeeService.editDetailsOfEmployee(firstName, lastName, employeeId));
    }

}
