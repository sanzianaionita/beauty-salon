package com.example.beautysalon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "STREET_NUMBER")
    private Integer streetNumber;

    @Column(name = "POSTAL_CODE")
    private Integer postalCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
