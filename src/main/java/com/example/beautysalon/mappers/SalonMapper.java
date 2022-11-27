package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.SalonDTO;
import com.example.beautysalon.model.Salon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface SalonMapper {


    SalonDTO salonToSalonDto(Salon salon);

    List<SalonDTO> salonToSalonDto(List<Salon> salons);

    Salon salonDtoToSalon (SalonDTO salonDto);

    List<Salon> salonDtoToSalon(List<SalonDTO> salonDTOS);
}

