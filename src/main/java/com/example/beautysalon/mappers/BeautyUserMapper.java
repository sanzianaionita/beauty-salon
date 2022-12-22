package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.BeautyUserDTO;
import com.example.beautysalon.model.BeautyUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BeautyUserMapper {

    BeautyUserDTO userToUserDTO (BeautyUser user);

    List<BeautyUserDTO> userToUserDTO (List<BeautyUser> user);

    BeautyUser userDtoToUser (BeautyUserDTO userDTO);

    List<BeautyUser> userDtoToUser (List<BeautyUserDTO> usersDTO);
}
