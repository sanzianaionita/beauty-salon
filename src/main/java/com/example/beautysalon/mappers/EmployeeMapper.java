package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.EmployeeDTO;
import com.example.beautysalon.mappers.resolvers.SalonMapperResolver;
import com.example.beautysalon.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SalonMapperResolver.class})
public interface EmployeeMapper {

    @Mapping(target = "salon", source = "salon.salonName")
    @Mapping(target = "salonId", source = "salon.id")
    EmployeeDTO employeeToEmployeeDto(Employee employee);

    List<EmployeeDTO> employeeToEmployeeDto(List<Employee> employees);

    @Mapping(target = "salon", source = "salonId")
    Employee employeeDtoToEmployee(EmployeeDTO employeeDTO);

    List<Employee> employeeDtoToEmployee(List<EmployeeDTO> employeeDTOS);
}
