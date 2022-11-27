package com.example.beautysalon.service;

import com.example.beautysalon.dto.EmployeeDTO;
import com.example.beautysalon.mappers.EmployeeMapper;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.Position;
import com.example.beautysalon.repository.EmployeeRepository;
import com.example.beautysalon.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.positionRepository = positionRepository;
    }

    public List<EmployeeDTO> getEmployeesByPosition(String functionName){
        List<Position> allByFunctionName = positionRepository.findAllByFunctionName(functionName);

        List<UUID> collect = allByFunctionName.stream()
                .map(Position::getId).toList();

        List<Employee> allByPositionIdIn = employeeRepository.findAllByPositionIdIn(collect);

        return employeeMapper.employeeToEmployeeDto(allByPositionIdIn);

    }
}
