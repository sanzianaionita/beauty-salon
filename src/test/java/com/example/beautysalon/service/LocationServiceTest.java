package com.example.beautysalon.service;

import com.example.beautysalon.dto.LocationDTO;
import com.example.beautysalon.mappers.LocationMapper;
import com.example.beautysalon.model.Location;
import com.example.beautysalon.repository.LocationRepository;
import com.example.beautysalon.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class LocationServiceTest {

    private static LocationService locationService;
    private static LocationRepository locationRepository;
    private static LocationMapper locationMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void setup() {
        locationRepository = mock(LocationRepository.class);
        locationMapper = mock(LocationMapper.class);
        locationService = new LocationService(locationRepository, locationMapper);
    }

    @Test
    public void findById(){
        when(locationRepository.findById(any())).thenReturn(Optional.of(Utils.createLocation()));
        when(locationMapper.locationToLocationDto(any(Location.class))).thenReturn(Utils.creaLocationDTO());

        LocationDTO byId = locationService.findById(UUID.randomUUID());

        assertNotNull(byId);
    }

    @Test
    public void getAllLocations(){
        when(locationRepository.findAll()).thenReturn(Collections.singletonList(Utils.createLocation()));
        when(locationMapper.locationToLocationDto(anyList())).thenReturn(Collections.singletonList(Utils.creaLocationDTO()));

        List<LocationDTO> allLocations = locationService.getAllLocations();

        assertNotEquals(0, allLocations.size());
    }
}
