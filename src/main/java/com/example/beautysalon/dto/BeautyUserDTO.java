package com.example.beautysalon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class BeautyUserDTO {
    private UUID id;

    private String email;

    private String password;
    private String authority;

    public BeautyUserDTO(BeautyUserDTO applicationUserDTO) {
        this.id = applicationUserDTO.getId();
        this.email = applicationUserDTO.getEmail();
        this.password = applicationUserDTO.getPassword();
        this.authority = applicationUserDTO.getAuthority();
    }

    public BeautyUserDTO() {
    }
}
