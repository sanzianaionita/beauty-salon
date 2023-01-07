package com.example.beautysalon.service;

import com.example.beautysalon.customExceptions.CustomException;
import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.dto.ServiceDTO;
import com.example.beautysalon.mappers.SalonMapper;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.repository.SalonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalonService {

    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;

    public SalonService(SalonRepository salonRepository,
                        SalonMapper salonMapper) {
        this.salonRepository = salonRepository;
        this.salonMapper = salonMapper;
    }

    public List<SalonDTO> getSalons() {
        List<Salon> all = salonRepository.findAll();
        return salonMapper.salonToSalonDto(all);
    }

    public List<SalonDTO> getSalonsByLocation(UUID locationId) {
        List<Salon> all = salonRepository.findAllByLocationId(locationId);
        return salonMapper.salonToSalonDto(all);
    }

    public SalonDTO getSalonsBySalonName(String salonName) {
        Salon salon = salonRepository.findBySalonName(salonName);

        if (salon == null) {
            throw new CustomException("Salon not found with name " + salonName, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }
        return salonMapper.salonToSalonDto(salon);
    }

    public SalonDTO createSalon(Salon salon) {
        Salon save = salonRepository.save(salon);
        return salonMapper.salonToSalonDto(save);
    }

}
