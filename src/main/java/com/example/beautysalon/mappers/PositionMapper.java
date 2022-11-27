package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.PositionDTO;
import com.example.beautysalon.model.Position;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionDTO positionToPositionDto (Position position);

    List<PositionDTO> positionToPositionDto (List<Position> positions);

    Position positionDtoToPosition (PositionDTO positionDTO);

    List<Position> positionDtoToPosition (List<PositionDTO> positionDTOS);

}
