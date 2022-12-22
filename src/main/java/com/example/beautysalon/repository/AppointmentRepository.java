package com.example.beautysalon.repository;

import com.example.beautysalon.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> findAllByAppointmentDateBetweenAndEmployeeIdAndSalonIdAndClientIdNot(LocalDateTime startDate, LocalDateTime endDate, UUID employeeId, UUID salonId, UUID clientId);

    List<Appointment> findAllBySalonId(UUID salonId);

    List<Appointment> findAllByClientId(UUID clientId);

    List<Appointment> findAllByEmployeeId(UUID employeeId);
}
