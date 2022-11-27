package com.example.beautysalon.dto;

import com.example.beautysalon.model.Location;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalonDTO implements Serializable {

    private UUID id;

    private String salonName;

    private String salonProgram;

    private LocationDTO location;

    private List<ServiceDTO> services;

    private List<EmployeeDTO> employees;

}