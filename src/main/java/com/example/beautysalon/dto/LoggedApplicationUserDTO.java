package com.example.beautysalon.dto;

public class LoggedApplicationUserDTO extends BeautyUserDTO {

    private String token;

    public LoggedApplicationUserDTO(BeautyUserDTO applicationUserDTO) {
        super(applicationUserDTO);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}