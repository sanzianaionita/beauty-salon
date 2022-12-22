package com.example.beautysalon.dto;

import com.example.beautysalon.validator.OnlyLetters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private UUID id;

    @OnlyLetters
    private String firstName;

    @OnlyLetters
    private String lastName;

    private String phoneNumber;

    private List<AppointmentDTO> appointment;
}
