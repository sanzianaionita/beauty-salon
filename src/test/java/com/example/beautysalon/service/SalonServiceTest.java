package com.example.beautysalon.service;

import com.example.beautysalon.dto.PositionDTO;
import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.mappers.PositionMapper;
import com.example.beautysalon.mappers.SalonMapperImpl;
import com.example.beautysalon.model.Position;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.repository.PositionRepository;
import com.example.beautysalon.repository.SalonRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalonServiceTest {
    private static SalonRepository salonRepository;
    private static SalonService salonService;
    private static SalonMapperImpl salonMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void setup() {
        salonRepository = mock(SalonRepository.class);
        salonMapper = mock(SalonMapperImpl.class);
        salonService = new SalonService(salonRepository,salonMapper);
    }

    @Test
    public void getSalons(){
        when(salonRepository.findAll()).thenReturn(Collections.singletonList(Utils.createSalon()));
        when(salonMapper.salonToSalonDto(anyList())).thenReturn(Collections.singletonList(Utils.createSalonDTO()));

        List<SalonDTO> salons = salonService.getSalons();

        assertNotEquals(0, salons.size());
    }

    @Test
    public void getSalonsByLocation(){
        when(salonRepository.findAllByLocationId(any())).thenReturn(Collections.singletonList(Utils.createSalon()));
        when(salonMapper.salonToSalonDto(anyList())).thenReturn(Collections.singletonList(Utils.createSalonDTO()));

        List<SalonDTO> salonsByLocation = salonService.getSalonsByLocation(UUID.randomUUID());

        assertNotEquals(0, salonsByLocation.size());
    }

    @Test
    public void getSalonsBySalonName(){
        when(salonRepository.findBySalonName(any())).thenReturn(Utils.createSalon());
        when(salonMapper.salonToSalonDto(any(Salon.class))).thenReturn(Utils.createSalonDTO());

        SalonDTO salonsBySalonName = salonService.getSalonsBySalonName(any());

        assertNotNull(salonsBySalonName);
    }

    @Test
    public void createSalon(){
        when(salonRepository.save(any())).thenReturn(Utils.createSalon());
        when(salonMapper.salonToSalonDto(any(Salon.class))).thenReturn(Utils.createSalonDTO());

        SalonDTO salon = salonService.createSalon(any());

        assertNotNull(salon);
    }

}
