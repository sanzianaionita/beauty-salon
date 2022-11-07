package com.example.beautysalon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SALON")
public class Salon {

    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "SALON_NAME")
    private String salonName;

    @Column(name = "SALON_PROGRAM")
    private String salonProgram;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @OneToMany(mappedBy = "salon")
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "salon")
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "SALON_SERVICE",
            joinColumns = {@JoinColumn(name = "SALON_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SERVICE_ID")})
    private Set<Service> services = new HashSet<>();

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
