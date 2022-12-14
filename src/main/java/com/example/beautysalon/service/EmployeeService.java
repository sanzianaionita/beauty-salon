package com.example.beautysalon.service;

import com.example.beautysalon.customExceptions.CustomException;
import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.dto.EmployeeDTO;
import com.example.beautysalon.mappers.AppointmentMapper;
import com.example.beautysalon.mappers.EmployeeMapper;
import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.Position;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.EmployeeRepository;
import com.example.beautysalon.repository.PositionRepository;
import com.example.beautysalon.security.util.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PositionRepository positionRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeMapper employeeMapper,
                           PositionRepository positionRepository,
                           AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.positionRepository = positionRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<EmployeeDTO> getEmployeesByPosition(String functionName) {
        List<Position> allByFunctionName = positionRepository.findAllByFunctionName(functionName);
        List<UUID> collect = allByFunctionName.stream()
                .map(Position::getId).toList();
        List<Employee> allByPositionIdIn = employeeRepository.findAllByPositionIdIn(collect);
        return employeeMapper.employeeToEmployeeDto(allByPositionIdIn);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        List<EmployeeDTO> employees = employeeMapper.employeeToEmployeeDto(allEmployees);
        return employees;
    }

    public AppointmentDTO confirmAppointment(UUID appointmentId, boolean confirmed) {

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isEmpty()) {
            throw new CustomException("Appointment does not exist!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        Appointment appointment = optionalAppointment.get();
        UUID userId = SecurityUtils.getUserId();

        if (userId == null) {
            throw new CustomException("User not logged in!", HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
        }

        employeeRepository.findByUserId(userId).ifPresent(employee -> {
            if (!employee.getId().equals(appointment.getEmployee().getId())) {
                throw new CustomException("Selected appointment is not included in your appointment list", HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
            }
        });

        appointment.setConfirmed(confirmed);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.appointmentToAppointmentDto(savedAppointment);

    }

    public EmployeeDTO editDetailsOfEmployee(String firstName, String lastName, UUID employeeId) {
        Optional<Employee> byId = employeeRepository.findById(employeeId);

        if (byId.isEmpty()) {
            throw new CustomException("Employee does not exist", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        UUID userId = SecurityUtils.getUserId();

        if (userId == null) {
            throw new CustomException("Employee not logged in!", HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
        }

        employeeRepository.findByUserId(userId).ifPresent(employee -> {
            if (!byId.get().getId().equals(employee.getId())) {
                throw new CustomException("Employee details not visible.", HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
            }
        });

        Employee employee = byId.get();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        Employee savedEmployee = employeeRepository.save(byId.get());

        return employeeMapper.employeeToEmployeeDto(savedEmployee);
    }
}
