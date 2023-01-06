package com.example.beautysalon.service;

import com.example.beautysalon.dto.ServiceDTO;
import com.example.beautysalon.mappers.ServiceMapperImpl;
import com.example.beautysalon.model.Service;
import com.example.beautysalon.repository.SalonRepository;
import com.example.beautysalon.repository.ServiceRepository;
import com.example.beautysalon.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceClassForServicesTest {
    private static ServiceClassForServices serviceClassForServices;
    private static ServiceRepository serviceRepository;
    private static ServiceMapperImpl serviceMapper;
    private static SalonRepository salonRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void setup() {
        salonRepository = mock(SalonRepository.class);
        serviceMapper = mock(ServiceMapperImpl.class);
        serviceRepository = mock(ServiceRepository.class);
        serviceClassForServices = new ServiceClassForServices(serviceRepository, salonRepository, serviceMapper);
    }

    @Test
    public void findById(){
        when(serviceRepository.findById(any())).thenReturn(Optional.of(Utils.createService()));
        when(serviceMapper.serviceToServiceDto(any(Service.class))).thenReturn(Utils.createServiceDTO());

        ServiceDTO byId = serviceClassForServices.findById(UUID.randomUUID());

        assertNotNull(byId);
    }

    @Test
    public void getServicesBySalon(){
        when(salonRepository.findBySalonName(any())).thenReturn(Utils.createSalon());
        when(serviceMapper.serviceToServiceDto(anyList())).thenReturn(Collections.singletonList(Utils.createServiceDTO()));

        Map<String, List<ServiceDTO>> servicesBySalon = serviceClassForServices.getServicesBySalon("test");

        assertNotNull(servicesBySalon);
    }
}
