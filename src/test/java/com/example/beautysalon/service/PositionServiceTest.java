package com.example.beautysalon.service;

import com.example.beautysalon.dto.LocationDTO;
import com.example.beautysalon.dto.PositionDTO;
import com.example.beautysalon.mappers.LocationMapper;
import com.example.beautysalon.mappers.PositionMapper;
import com.example.beautysalon.model.Position;
import com.example.beautysalon.repository.LocationRepository;
import com.example.beautysalon.repository.PositionRepository;
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

public class PositionServiceTest {

    private static PositionService positionService;
    private static PositionRepository positionRepository;
    private static PositionMapper positionMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void setup() {
        positionRepository = mock(PositionRepository.class);
        positionMapper = mock(PositionMapper.class);
        positionService = new PositionService(positionRepository,positionMapper);
    }

    @Test
    public void getById(){
        when(positionRepository.findById(any())).thenReturn(Optional.of(Utils.createPosition()));
        when(positionMapper.positionToPositionDto(any(Position.class))).thenReturn(Utils.createPositionDTO());

        PositionDTO byId = positionService.getById(UUID.randomUUID());

        assertNotNull(byId);
    }

    @Test
    public void getAllPositions(){
        when(positionRepository.findAll()).thenReturn(Collections.singletonList(Utils.createPosition()));
        when(positionMapper.positionToPositionDto(anyList())).thenReturn(Collections.singletonList(Utils.createPositionDTO()));

        List<PositionDTO> allPositions = positionService.getAllPositions();

        assertNotEquals(0, allPositions.size());
    }
}
