package com.example.beautysalon.service;

import com.example.beautysalon.dto.LocationDTO;
import com.example.beautysalon.dto.PositionDTO;
import com.example.beautysalon.mappers.LocationMapper;
import com.example.beautysalon.model.Location;
import com.example.beautysalon.model.Position;
import com.example.beautysalon.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public LocationDTO findById(UUID id) {
        Optional<Location> byId = locationRepository.findById(id);

        return byId.map(locationMapper::locationToLocationDto).orElse(null);
    }

    public List<LocationDTO> getAllLocations() {

        List<Location> allLocations = locationRepository.findAll();
        return locationMapper.locationToLocationDto(allLocations);
    }
}
