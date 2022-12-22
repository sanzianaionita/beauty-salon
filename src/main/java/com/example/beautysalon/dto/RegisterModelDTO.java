package com.example.beautysalon.dto;

public class RegisterModelDTO {
    private BeautyUserDTO user;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public BeautyUserDTO getUser() {
        return user;
    }

    public void setUserDTO(BeautyUserDTO user) {
        this.user = user;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
