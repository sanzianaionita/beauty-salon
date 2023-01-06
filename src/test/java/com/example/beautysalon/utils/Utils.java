package com.example.beautysalon.utils;

import com.example.beautysalon.dto.*;
import com.example.beautysalon.model.*;

import java.util.UUID;

public class Utils {

    public static Appointment returnAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(UUID.randomUUID());
        appointment.setConfirmed(true);

        appointment.setEmployee(createEmployee());
        appointment.setClient(createClient());
        return appointment;
    }

    public static AppointmentDTO returnAppointmentDto() {
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setId(UUID.randomUUID());
        appointment.setConfirmed(true);

        appointment.setEmployee("test");
        appointment.setClientId(UUID.randomUUID());
        return appointment;
    }

    public static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setLastName("test");
        employee.setFirstName("test");
        employee.setId(UUID.randomUUID());

        return employee;
    }

    public static EmployeeDTO createEmployeeDTO(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(UUID.randomUUID());
        employeeDTO.setFirstName("test");
        employeeDTO.setLastName("test");

        return employeeDTO;
    }

    public static Client createClient() {
        Client client = new Client();
        client.setLastName("test");
        client.setFirstName("test");
        client.setPhoneNumber("test");
        client.setId(UUID.randomUUID());

        return client;
    }

    public static ClientDTO returnClientDto() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setFirstName("test");
        clientDTO.setLastName("test");
        clientDTO.setPhoneNumber("test");

        return clientDTO;
    }

    public static Salon createSalon() {
        Salon salon = new Salon();
        salon.setId(UUID.randomUUID());

        return salon;
    }

    public static SalonDTO createSalonDTO(){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(UUID.randomUUID());

        return salonDTO;
    }

    public static Service createService() {
        Service service = new Service();
        service.setId(UUID.randomUUID());

        return service;
    }

    public static ServiceDTO createServiceDTO(){
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(UUID.randomUUID());

        return serviceDTO;
    }


    public static Position createPosition(){
        Position position = new Position();

        position.setId(UUID.randomUUID());
        position.setFunctionName("test");
        position.setFunctionDescription("test");
        position.setCorCode(1);

        return position;
    }

    public static PositionDTO createPositionDTO(){
        PositionDTO positionDTO = new PositionDTO();

        positionDTO.setId(UUID.randomUUID());
        positionDTO.setFunctionName("test");
        positionDTO.setFunctionDescription("test");
        positionDTO.setCorCode(1);

        return positionDTO;
    }

    public static Location createLocation(){
        Location location = new Location();

        location.setId(UUID.randomUUID());
        location.setStreet("test");
        location.setStreetNumber(1);
        location.setPostalCode(1);

        return location;
    }

    public static LocationDTO creaLocationDTO(){
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setId(UUID.randomUUID());
        locationDTO.setStreet("test");
        locationDTO.setPostalCode(1);
        locationDTO.setStreetNumber(1);

        return locationDTO;
    }
}
