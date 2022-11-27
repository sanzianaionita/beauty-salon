package com.example.beautysalon.service;

import com.example.beautysalon.mappers.LocationMapper;
import com.example.beautysalon.model.Location;
import com.example.beautysalon.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public Location findById(UUID id) {
        Optional<Location> byId = locationRepository.findById(id);
        return byId.orElse(null);
    }
}
