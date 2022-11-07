package com.example.beautysalon.service;

import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.mappers.SalonMapper;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.repository.SalonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalonService {

    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;

    public SalonService(SalonRepository salonRepository, SalonMapper salonMapper) {
        this.salonRepository = salonRepository;
        this.salonMapper = salonMapper;
    }

    public List<SalonDTO> getSalons() {
        List<Salon> all = salonRepository.findAll();
        return salonMapper.toDto(all);
    }

    public List<SalonDTO> getSalonsByLocation(UUID locationId) {
        List<Salon> all = salonRepository.findAllByLocationId(locationId);
        return salonMapper.toDto(all);
    }

    public List<SalonDTO> getSalonsBySalonName(String salonName) {
        List<Salon> all = salonRepository.findAllBySalonName(salonName);
        return salonMapper.toDto(all);
    }

    public SalonDTO createSalon(Salon salon) {
        Salon save = salonRepository.save(salon);
        return salonMapper.toDto(save);
    }
}
