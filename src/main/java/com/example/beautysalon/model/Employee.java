package com.example.beautysalon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "WAGE")
    private Integer wage;

    @Column(name = "EMPLOYMENT_DATE")
    private LocalDate employmentDate;

    @OneToOne
    @JoinColumn(name = "POSITION_ID")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "SALON_ID")
    private Salon salon;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getWage() {
        return wage;
    }

    public void setWage(Integer wage) {
        this.wage = wage;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}
