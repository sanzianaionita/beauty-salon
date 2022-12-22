package com.example.beautysalon.service;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.mappers.AppointmentMapper;
import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.ClientRepository;
import com.example.beautysalon.security.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, ClientRepository clientRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.clientRepository = clientRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public AppointmentDTO findById(UUID id) {
        Optional<Appointment> byId = appointmentRepository.findById(id);
        return byId.map(appointmentMapper::appointmentToAppointmentDto).orElse(null);
    }

    public AppointmentDTO createAppointment(UUID salonId, UUID serviceId, UUID employeeId, UUID clientId, String appointmentDate) {
        Instant instant = Instant.parse(appointmentDate);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        LocalDateTime startDate = dateTime.minus(59, ChronoUnit.MINUTES);
        LocalDateTime endDate = dateTime.plus(59, ChronoUnit.MINUTES);

        List<Appointment> allByAppointmentDateBetween = appointmentRepository.findAllByAppointmentDateBetweenAndEmployeeIdAndSalonIdAndClientIdNot(startDate, endDate, employeeId, salonId, clientId);

        if (!allByAppointmentDateBetween.isEmpty()) {
            throw new RuntimeException("Appointment date overlaps");
        }

        List<Appointment> allByClientId = appointmentRepository.findAllByClientId(clientId);
        Optional<Appointment> first = allByClientId
                .stream()
                .filter(appointment -> appointment.getAppointmentDate().equals(dateTime))
                .findFirst();

        if (first.isPresent()) {

            return editAppointment(appointmentMapper.appointmentToAppointmentDto(first.get()));

        } else {
            AppointmentDTO appointment = new AppointmentDTO();

            appointment.setSalonId(salonId);
            appointment.setServiceId(serviceId);
            appointment.setEmployeeId(employeeId);

            //TODO: change when frontend is ready
            appointment.setClientId(clientId);
            appointment.setAppointmentDate(dateTime);
            appointment.setConfirmed(false);

            Appointment savedAppointment = appointmentRepository.save(appointmentMapper.appointmentDtoToAppointment(appointment));

            return appointmentMapper.appointmentToAppointmentDto(savedAppointment);
        }
    }

    public AppointmentDTO editAppointment(AppointmentDTO appointmentDTO) {
        Optional<Appointment> byId = appointmentRepository.findById(appointmentDTO.getId());

        if (byId.isEmpty()) {
            throw new RuntimeException("Appointment does not exist");
        }

        LocalDateTime startDate = appointmentDTO.getAppointmentDate().minus(59, ChronoUnit.MINUTES);
        LocalDateTime endDate = appointmentDTO.getAppointmentDate().plus(59, ChronoUnit.MINUTES);

        List<Appointment> allByAppointmentDateBetween = appointmentRepository.findAllByAppointmentDateBetweenAndEmployeeIdAndSalonIdAndClientIdNot(startDate, endDate, appointmentDTO.getEmployeeId(), appointmentDTO.getSalonId(), appointmentDTO.getClientId());

        if (!allByAppointmentDateBetween.isEmpty()) {
            throw new RuntimeException("Appointment date overlaps");
        }

        UUID userId = SecurityUtils.getUserId();

        if (userId == null) {
            throw new RuntimeException("User not logged in!");
        }

       clientRepository.findByUserId(userId).ifPresent(client -> {
           if (!byId.get().getClient().getId().equals(client.getId())) {
               throw new RuntimeException("Appointment is not included in your appointment list!");
           }
       });


        Appointment saveAppointment = appointmentRepository.save(appointmentMapper.appointmentDtoToAppointment(appointmentDTO));

        return appointmentMapper.appointmentToAppointmentDto(saveAppointment);
    }

    public int deleteAppointment(UUID appointmentId) {
        Optional<Appointment> byId = appointmentRepository.findById(appointmentId);
        if (byId.isEmpty()) {
            return -1;
        }

        appointmentRepository.deleteById(appointmentId);

        return 1;
    }

    public List<AppointmentDTO> getAllAppointmentsFromSalon(UUID salonId) {
        List<Appointment> allBySalonId = appointmentRepository.findAllBySalonId(salonId);

        return appointmentMapper.appointmentToAppointmentDto(allBySalonId);
    }

    public List<AppointmentDTO> getAllAppointmentsForClient(UUID clientId) {
        List<Appointment> allBySalonId = appointmentRepository.findAllByClientId(clientId);

        return appointmentMapper.appointmentToAppointmentDto(allBySalonId);
    }

    public List<AppointmentDTO> getAllAppointmentsForEmployee(UUID employeeId) {
        List<Appointment> allBySalonId = appointmentRepository.findAllByEmployeeId(employeeId);

        return appointmentMapper.appointmentToAppointmentDto(allBySalonId);
    }
}