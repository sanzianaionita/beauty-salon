package com.example.beautysalon.security;

import com.example.beautysalon.model.BeautyUser;
import com.example.beautysalon.model.Client;
import com.example.beautysalon.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class CustomUserDetails extends User {
    private UUID id;
    private BeautyUser user;

    public CustomUserDetails(String username,
                             String password,
                             Collection<? extends GrantedAuthority> authorities,
                             UUID id,
                             BeautyUser user) {
        super(username, password, authorities);
        this.id = id;
        this.user = user;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BeautyUser getUser() {
        return user;
    }

    public void setUser(BeautyUser user) {
        this.user = user;
    }
}
