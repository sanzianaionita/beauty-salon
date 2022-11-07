package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.model.Salon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SalonMapper {
    private final ServiceMapper serviceMapper;

    public SalonMapper(ServiceMapper serviceMapper) {
        this.serviceMapper = serviceMapper;
    }

    public SalonDTO toDto(Salon entity) {
        SalonDTO salonDTO = new SalonDTO();

        salonDTO.setSalonName(entity.getSalonName());
        salonDTO.setSalonProgram(entity.getSalonProgram());
        salonDTO.setId(entity.getId());
        salonDTO.setLocation(entity.getLocation());
        salonDTO.setServices(serviceMapper.toDto(new ArrayList<>(entity.getServices())));

        return salonDTO;
    }

    public List<SalonDTO> toDto(List<Salon> entity) {
        List<SalonDTO> salons = new ArrayList<>();
        for (Salon salon :
                entity) {
            SalonDTO salonDTO = new SalonDTO();

            salonDTO.setSalonName(salon.getSalonName());
            salonDTO.setSalonProgram(salon.getSalonProgram());
            salonDTO.setId(salon.getId());
            salonDTO.setLocation(salon.getLocation());
            salonDTO.setServices(serviceMapper.toDto(new ArrayList<>(salon.getServices())));

            salons.add(salonDTO);
        }

        return salons;
    }


}
