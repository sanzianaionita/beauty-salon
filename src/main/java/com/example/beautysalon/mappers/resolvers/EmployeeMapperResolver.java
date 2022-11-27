package com.example.beautysalon.mappers.resolvers;

import com.example.beautysalon.model.Employee;
import com.example.beautysalon.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EmployeeMapperResolver {

    private final EmployeeRepository employeeRepository;

    public EmployeeMapperResolver(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findById (UUID id){
        Optional<Employee> byId = employeeRepository.findById(id);
        return byId.orElse(null);
    }
}
