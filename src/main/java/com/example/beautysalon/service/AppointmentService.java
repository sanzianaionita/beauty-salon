package com.example.beautysalon.service;

import com.example.beautysalon.mappers.AppointmentMapper;
import com.example.beautysalon.model.Appointment;
import com.example.beautysalon.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public Appointment findById(UUID id) {
        Optional<Appointment> byId = appointmentRepository.findById(id);
        return byId.orElse(null);
    }
}
