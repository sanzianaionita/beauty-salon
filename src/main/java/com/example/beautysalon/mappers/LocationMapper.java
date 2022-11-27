package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.LocationDTO;
import com.example.beautysalon.model.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDTO locationToLocationDto (Location location);

    List<LocationDTO> locationToLocationDto (List<Location> locations);

    Location locationDtoToLocation (LocationDTO locationDTO);

    List<Location> locationDtoToLocation (List<LocationDTO> locationDTOS);
}
