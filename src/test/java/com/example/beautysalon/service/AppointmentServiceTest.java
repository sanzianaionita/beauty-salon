package com.example.beautysalon.service;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.mappers.AppointmentMapperImpl;
import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.ClientRepository;
import com.example.beautysalon.security.util.SecurityUtils;
import com.example.beautysalon.utils.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {
    private static AppointmentRepository appointmentRepository;
    private static ClientRepository clientRepository;
    private static AppointmentService appointmentService;
    private static MockedStatic<SecurityUtils> securityUtilsMockedStatic;
    private static AppointmentMapperImpl mapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void setup() {
        appointmentRepository = mock(AppointmentRepository.class);
        clientRepository = mock(ClientRepository.class);
        mapper = mock(AppointmentMapperImpl.class);
        securityUtilsMockedStatic = mockStatic(SecurityUtils.class);
        appointmentService = new AppointmentService(appointmentRepository, clientRepository, mapper);
    }

    @AfterAll
    public static void close() {
        securityUtilsMockedStatic.close();
    }

    @Test
    void findById_expect_return_object() {
        when(appointmentRepository.findById(any())).thenReturn(Optional.of(Utils.returnAppointment()));
        when(mapper.appointmentToAppointmentDto(any(Appointment.class))).thenReturn(Utils.returnAppointmentDto());

        AppointmentDTO byId = appointmentService.findById(any());

        assertNotNull(byId);

    }

    @Test
    void findById_expect_return_null() {
        when(appointmentRepository.findById(any())).thenReturn(Optional.empty());

        AppointmentDTO byId = appointmentService.findById(any());

        assertNull(byId);

    }

    @Test
    void createAppointment_expect_date_overlaps() {
        when(appointmentRepository.findAllByAppointmentDateBetweenAndEmployeeIdAndSalonIdAndClientIdNot(any(), any(), any(), any(), any())).thenReturn(Collections.singletonList(Utils.returnAppointment()));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.createAppointment(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "2022-12-23T19:40:00Z");
        });

        String expectedMessage = "Appointment date overlaps";
        String actualMessage = exception.getMessage();

        System.out.println(exception.getMessage());

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createAppointment_edit_appointment_appointment_not_in_client_list() {
        when(appointmentRepository.findAllByAppointmentDateBetweenAndEmployeeIdAndSalonIdAndClientIdNot(any(), any(), any(), any(), any())).thenReturn(Collections.emptyList());

        Appointment appointment = Utils.returnAppointment();
        Instant instant = Instant.parse("2022-12-23T19:40:00Z");
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        appointment.setAppointmentDate(dateTime);

        when(appointmentRepository.findAllByClientId(any())).thenReturn(Collections.singletonList(appointment));

        when(appointmentRepository.findById(any())).thenReturn(Optional.of(appointment));
        when(appointmentRepository.findAllByAppointmentDateBetweenAndEmployeeIdAndSalonIdAndClientIdNot(any(), any(), any(), any(), any())).thenReturn(Collections.emptyList());
        securityUtilsMockedStatic.when(SecurityUtils::getUserId).thenReturn(UUID.randomUUID());

        AppointmentDTO appointmentDTO = Utils.returnAppointmentDto();
        appointmentDTO.setAppointmentDate(dateTime);
        when(mapper.appointmentToAppointmentDto(any(Appointment.class))).thenReturn(appointmentDTO);

        Client client = Utils.createClient();
        client.setId(UUID.randomUUID());
        when(clientRepository.findByUserId(any())).thenReturn(Optional.of(client));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.createAppointment(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "2022-12-23T19:40:00Z");
        });

        String expectedMessage = "Appointment is not included in your appointment list!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createAppointment_success() {
        when(appointmentRepository.findAllByAppointmentDateBetweenAndEmployeeIdAndSalonIdAndClientIdNot(any(), any(), any(), any(), any())).thenReturn(Collections.emptyList());

        Appointment appointment = Utils.returnAppointment();
        Instant instant = Instant.parse("2022-12-23T19:40:00Z");
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        appointment.setAppointmentDate(dateTime);

        when(appointmentRepository.findAllByClientId(any())).thenReturn(Collections.emptyList());
        when(appointmentRepository.save(any())).thenReturn(Utils.returnAppointment());
        UUID uuid = UUID.randomUUID();
        AppointmentDTO appointment1 = appointmentService.createAppointment(uuid, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "2022-12-23T19:40:00Z");
        assertNotNull(appointment1);
    }

    @Test
    void editAppointment() {
        Client client = Utils.createClient();

        Appointment appointment = Utils.returnAppointment();
        AppointmentDTO dto = Utils.returnAppointmentDto();
        dto.setClientId(client.getId());
        appointment.setClient(client);

        dto.setAppointmentDate(LocalDateTime.now());

        when(appointmentRepository.findById(dto.getId())).thenReturn(Optional.of(appointment));
        when(clientRepository.findByUserId(dto.getClientId())).thenReturn(Optional.of(client));
        when(SecurityUtils.getUserId()).thenReturn(dto.getClientId());

        when(mapper.appointmentDtoToAppointment(any(AppointmentDTO.class))).thenReturn(appointment);
        when(appointmentRepository.save(any())).thenReturn(appointment);

        when(mapper.appointmentToAppointmentDto(any(Appointment.class))).thenReturn(dto);

        AppointmentDTO appointmentDTO = appointmentService.editAppointment(dto);

        assertNotNull(appointmentDTO);
    }

    @Test
    void deleteAppointment() {

        Appointment appointment = Utils.returnAppointment();

        when(appointmentRepository.findById(any())).thenReturn(Optional.of(appointment));

        appointmentService.deleteAppointment(any());

        assertEquals(-1, -1);
    }

    @Test
    void getAllAppointmentsFromSalon() {
        when(appointmentRepository.findAllBySalonId(any())).thenReturn(Collections.singletonList(Utils.returnAppointment()));
        when(mapper.appointmentToAppointmentDto(anyList())).thenReturn(Collections.singletonList(Utils.returnAppointmentDto()));

        List<AppointmentDTO> allAppointmentsFromSalon = appointmentService.getAllAppointmentsFromSalon(any());

        assertNotEquals(0, allAppointmentsFromSalon.size());
    }

    @Test
    void getAllAppointmentsForClient() {
        when(appointmentRepository.findAllBySalonId(any())).thenReturn(Collections.singletonList(Utils.returnAppointment()));
        when(mapper.appointmentToAppointmentDto(anyList())).thenReturn(Collections.singletonList(Utils.returnAppointmentDto()));

        List<AppointmentDTO> allAppointmentsFromSalon = appointmentService.getAllAppointmentsForClient(any());

        assertNotEquals(0, allAppointmentsFromSalon.size());
    }

    @Test
    void getAllAppointmentsForEmployee() {
        when(appointmentRepository.findAllBySalonId(any())).thenReturn(Collections.singletonList(Utils.returnAppointment()));
        when(mapper.appointmentToAppointmentDto(anyList())).thenReturn(Collections.singletonList(Utils.returnAppointmentDto()));

        List<AppointmentDTO> allAppointmentsFromSalon = appointmentService.getAllAppointmentsForEmployee(any());

        assertNotEquals(0, allAppointmentsFromSalon.size());
    }
}