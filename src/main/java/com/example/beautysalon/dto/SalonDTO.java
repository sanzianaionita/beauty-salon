package com.example.beautysalon.dto;

import com.example.beautysalon.model.Location;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class SalonDTO implements Serializable {
    private UUID id;
    private String salonName;
    private String salonProgram;
    private Location location;
    private List<ServiceDTO> services;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonProgram() {
        return salonProgram;
    }

    public void setSalonProgram(String salonProgram) {
        this.salonProgram = salonProgram;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }
}
