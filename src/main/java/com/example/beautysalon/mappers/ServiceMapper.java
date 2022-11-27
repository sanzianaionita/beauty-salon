package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.ServiceDTO;
import com.example.beautysalon.model.Service;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO serviceToServiceDto (Service service);

    List<ServiceDTO> serviceToServiceDto (List<Service> services);

    Service serviceDtoToService (ServiceDTO serviceDTO);

    List<Service> serviceDtoToService (List<ServiceDTO> serviceDTOS);

}
