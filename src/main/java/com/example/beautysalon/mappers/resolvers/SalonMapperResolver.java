package com.example.beautysalon.mappers.resolvers;

import com.example.beautysalon.model.Salon;
import com.example.beautysalon.repository.SalonRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SalonMapperResolver {
    private final SalonRepository salonRepository;

    public SalonMapperResolver(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    public Salon findById(UUID id) {
        Optional<Salon> byId = salonRepository.findById(id);
        return byId.orElse(null);
    }
}
