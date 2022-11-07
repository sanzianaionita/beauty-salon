package com.example.beautysalon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "APPOINTMENT_DATE")
    private LocalDateTime appointmentDate;

    @Column(name = "CLIENT_FIRST_NAME")
    private String clientFirstName;

    @Column(name = "CLIENT_LAST_NAME")
    private String clientLastName;

    @Column(name = "CLIENT_PHONE_NUMBER")
    private Integer clientPhoneNumber;

    @Column(name = "CONFIRMED")
    private Boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "SALON_ID")
    private Salon salon;

    @OneToOne
    @JoinColumn(name = "SERVICE_ID")
    private Service service;

    @OneToOne
    @JoinColumn(name = "EMPLOYEES_ID")
    private Employee employee;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public Integer getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(Integer clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
