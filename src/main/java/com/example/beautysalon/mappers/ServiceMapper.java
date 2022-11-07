package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.ServiceDTO;
import com.example.beautysalon.model.Salon;
import com.example.beautysalon.model.Service;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    public ServiceDTO toDto(Service entity) {
        ServiceDTO serviceDTO = new ServiceDTO();

        serviceDTO.setServiceName(entity.getServiceName());
        serviceDTO.setServiceDescription(entity.getServiceDescription());
        serviceDTO.setServicePrice(entity.getServicePrice());
        serviceDTO.setId(entity.getId());

        return serviceDTO;
    }

    public List<ServiceDTO> toDto(List<Service> entity) {
        List<ServiceDTO> serviceDTOList = new ArrayList<>();
        for (Service serv :
                entity) {
            ServiceDTO serviceDTO = new ServiceDTO();

            serviceDTO.setServiceName(serv.getServiceName());
            serviceDTO.setServiceDescription(serv.getServiceDescription());
            serviceDTO.setServicePrice(serv.getServicePrice());
            serviceDTO.setId(serv.getId());
            serviceDTOList.add(serviceDTO);
        }

        return serviceDTOList;
    }
}
