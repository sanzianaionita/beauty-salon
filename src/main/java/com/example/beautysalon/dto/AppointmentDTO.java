package com.example.beautysalon.dto;

import com.example.beautysalon.validator.OnlyLetters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO implements Serializable {

    private UUID id;

    private LocalDateTime appointmentDate;

    @OnlyLetters
    private String clientName;
    private String clientPhoneNumber;
    private UUID clientId;

    private Boolean confirmed;

    private String salon;
    private UUID salonId;

    private String service;
    private UUID serviceId;

    @OnlyLetters
    private String employee;
    private UUID employeeId;

}
