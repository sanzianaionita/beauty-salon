package com.example.beautysalon.dto;

import com.example.beautysalon.validator.OnlyLetters;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    private UUID id;

    @NotNull
    @OnlyLetters
    private String firstName;

    @NotNull
    @OnlyLetters
    private String lastName;

    private PositionDTO position;

    private String salon;

    @JsonIgnore
    private UUID salonId;

}
