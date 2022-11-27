package com.example.beautysalon.controller;

import com.example.beautysalon.dto.EmployeeDTO;
import com.example.beautysalon.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/allByFunction")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByPosition(@RequestParam String functionName){

        List<EmployeeDTO> employeesByPosition = employeeService.getEmployeesByPosition(functionName);

        return ResponseEntity.ok(employeesByPosition);
    }

}
