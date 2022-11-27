package com.example.beautysalon.dto;

import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.model.Service;
import com.example.beautysalon.validator.OnlyLetters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    private String clientFirstName;

    @OnlyLetters
    private String clientLastName;

    private Integer clientPhoneNumber;

    private Boolean confirmed;

    private String salon;
    private UUID salonId;

   private String service;
    private UUID serviceId;

    @OnlyLetters
    private String employee;
    private UUID employeeId;

}
