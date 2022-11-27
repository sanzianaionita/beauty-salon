package com.example.beautysalon.service;

import com.example.beautysalon.mappers.PositionMapper;
import com.example.beautysalon.model.Position;
import com.example.beautysalon.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    public PositionService(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    public Position getById(UUID id) {
        Optional<Position> byId = positionRepository.findById(id);
        return byId.orElse(null);
    }
}
