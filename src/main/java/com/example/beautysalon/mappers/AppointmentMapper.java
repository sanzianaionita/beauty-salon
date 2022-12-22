package com.example.beautysalon.mappers;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.mappers.resolvers.ClientMapperResolver;
import com.example.beautysalon.mappers.resolvers.EmployeeMapperResolver;
import com.example.beautysalon.mappers.resolvers.SalonMapperResolver;
import com.example.beautysalon.mappers.resolvers.ServiceMapperResolver;
import com.example.beautysalon.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SalonMapperResolver.class, ServiceMapperResolver.class, EmployeeMapperResolver.class, ClientMapperResolver.class})
public interface AppointmentMapper {

    @Mapping(target = "salon", source = "salon.salonName")
    @Mapping(target = "service", source = "service.serviceName")
    @Mapping(target = "employee", expression = "java(appointment.getEmployee().getFirstName() + \" \" + appointment.getEmployee().getLastName())")
    @Mapping(target = "salonId", source = "salon.id")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "clientName", expression = "java(appointment.getClient().getFirstName() + \" \" + appointment.getClient().getLastName())")
    @Mapping(target = "clientPhoneNumber", source = "client.phoneNumber")
    @Mapping(target = "clientId", source = "client.id")
    AppointmentDTO appointmentToAppointmentDto(Appointment appointment);

    List<AppointmentDTO> appointmentToAppointmentDto(List<Appointment> appointments);

    @Mapping(target = "salon", source = "salonId")
    @Mapping(target = "service", source = "serviceId")
    @Mapping(target = "employee", source = "employeeId")
    @Mapping(target = "client", source = "clientId")
    Appointment appointmentDtoToAppointment(AppointmentDTO appointmentDTO);

    List<Appointment> appointmentDtoToAppointment(List<AppointmentDTO> appointmentDTOS);
}