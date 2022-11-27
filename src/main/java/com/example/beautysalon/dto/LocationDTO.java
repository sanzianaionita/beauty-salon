package com.example.beautysalon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO implements Serializable {

    private UUID id;

    private String street;

    private Integer streetNumber;

    private Integer postalCode;

}
