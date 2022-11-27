package com.example.beautysalon.service;

import com.example.beautysalon.mappers.ServiceMapper;
import com.example.beautysalon.repository.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceClassForServices {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceClassForServices(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }
}
