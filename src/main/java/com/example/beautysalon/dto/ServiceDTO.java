package com.example.beautysalon.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO implements Serializable {

    private UUID id;

    private String serviceName;

    private String servicePrice;

    private String serviceDescription;

}
