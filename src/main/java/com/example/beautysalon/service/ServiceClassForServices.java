package com.example.beautysalon.service;

import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.dto.ServiceDTO;
import com.example.beautysalon.mappers.ServiceMapper;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.repository.SalonRepository;
import com.example.beautysalon.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceClassForServices {

    private final ServiceRepository serviceRepository;
    private final SalonRepository salonRepository;
    private final ServiceMapper serviceMapper;

    public ServiceClassForServices(ServiceRepository serviceRepository, SalonRepository salonRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.salonRepository = salonRepository;
        this.serviceMapper = serviceMapper;
    }

    public ServiceDTO findById(UUID id) {
        Optional<com.example.beautysalon.model.Service> byId = serviceRepository.findById(id);
        return byId.map(serviceMapper::serviceToServiceDto).orElse(null);
    }

    public Map<String, List<ServiceDTO>> getServicesBySalon(String salonName) {
        Salon salon = salonRepository.findBySalonName(salonName);

        if (salon == null) {
          return Collections.emptyMap();
        }
        List<com.example.beautysalon.model.Service> services = new ArrayList<>(salon.getServices());
        List<ServiceDTO> serviceDTOList = serviceMapper.serviceToServiceDto(services);

        Map<String, List<ServiceDTO>> servicesByCategory = new HashMap<>();

        serviceDTOList.forEach(service -> {
            servicesByCategory.computeIfAbsent(service.getServiceName(), k -> new ArrayList<>()).add(service);
        });


        return servicesByCategory;
    }
}
