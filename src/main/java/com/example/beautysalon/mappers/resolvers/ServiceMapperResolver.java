package com.example.beautysalon.mappers.resolvers;

import com.example.beautysalon.model.Service;
import com.example.beautysalon.repository.ServiceRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ServiceMapperResolver {
    private final ServiceRepository serviceRepository;

    public ServiceMapperResolver(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service findById(UUID id) {
        Optional<Service> byId = serviceRepository.findById(id);
        return byId.orElse(null);
    }
}
