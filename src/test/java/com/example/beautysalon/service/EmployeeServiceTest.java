package com.example.beautysalon.service;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.dto.EmployeeDTO;
import com.example.beautysalon.mappers.AppointmentMapperImpl;
import com.example.beautysalon.mappers.EmployeeMapperImpl;
import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.model.Employee;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.EmployeeRepository;
import com.example.beautysalon.repository.PositionRepository;
import com.example.beautysalon.security.util.SecurityUtils;
import com.example.beautysalon.utils.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    private static EmployeeRepository employeeRepository;
    private static EmployeeMapperImpl employeeMapper;
    private static PositionRepository positionRepository;
    private static AppointmentRepository appointmentRepository;
    private static AppointmentMapperImpl appointmentMapper;
    private static MockedStatic<SecurityUtils> securityUtilsMockedStatic;
    private static EmployeeService employeeService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void setup() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeMapper = mock(EmployeeMapperImpl.class);
        positionRepository = mock(PositionRepository.class);
        appointmentRepository = mock(AppointmentRepository.class);
        appointmentMapper = mock(AppointmentMapperImpl.class);
        securityUtilsMockedStatic = mockStatic(SecurityUtils.class);
        employeeService = new EmployeeService(employeeRepository, employeeMapper, positionRepository, appointmentRepository, appointmentMapper);
    }

    @AfterAll
    public static void close() {
        securityUtilsMockedStatic.close();
    }

    @Test
    public void getEmployeesByPosition(){

        EmployeeDTO employeeDTO = Utils.createEmployeeDTO();

        when(positionRepository.findAllByFunctionName(any())).thenReturn(Collections.emptyList());
        when(employeeRepository.findAllByPositionIdIn(any())).thenReturn(Collections.emptyList());
        when(employeeMapper.employeeToEmployeeDto(any(Employee.class))).thenReturn(employeeDTO);
        List<EmployeeDTO> employeesByPosition = employeeService.getEmployeesByPosition(any());

        assertNotNull(employeesByPosition);
    }

    @Test
    public void getAllEmployees(){

        EmployeeDTO employeeDTO = Utils.createEmployeeDTO();

        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        when(employeeMapper.employeeToEmployeeDto(any(Employee.class))).thenReturn(employeeDTO);

        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();

        assertNotNull(allEmployees);
    }

    @Test
    public void confirmAppointment(){

        AppointmentDTO dto = Utils.returnAppointmentDto();
        Employee employee = Utils.createEmployee();
        Appointment appointment = Utils.returnAppointment();
        appointment.setEmployee(employee);

        when(appointmentRepository.findById(any())).thenReturn(Optional.of(appointment));

        securityUtilsMockedStatic.when(SecurityUtils::getUserId).thenReturn(UUID.randomUUID());

        when(employeeRepository.findByUserId(any())).thenReturn(Optional.of(employee));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        when(appointmentMapper.appointmentToAppointmentDto(appointment)).thenReturn(dto);

        AppointmentDTO appointmentDTO = employeeService.confirmAppointment(UUID.randomUUID(), true);

        assertNotNull(appointmentDTO);
    }

    @Test
    public void editDetailsOfEmployee(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = Utils.createEmployee();

        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        securityUtilsMockedStatic.when(SecurityUtils::getUserId).thenReturn(UUID.randomUUID());
        when(employeeRepository.findByUserId(any())).thenReturn(Optional.of(employee));
        when(employeeMapper.employeeToEmployeeDto(employee)).thenReturn(employeeDTO);
        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeDTO employeeDTO1 = employeeService.editDetailsOfEmployee("test", "test", UUID.randomUUID());

        assertNotNull(employeeDTO1);
    }

}

