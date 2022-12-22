package com.example.beautysalon.security.service;


import com.example.beautysalon.model.BeautyUser;
import com.example.beautysalon.repository.BeautyUserRepository;
import com.example.beautysalon.security.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class SecurityUserService implements UserDetailsService {
    private final BeautyUserRepository userRepository;

    public SecurityUserService(BeautyUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<BeautyUser> byUserName = userRepository.findByEmail(email);
        if (byUserName.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        BeautyUser user = byUserName.get();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthority().name());
        return new CustomUserDetails(user.getEmail(), user.getPassword(), Collections.singleton(authority), user.getId(), user);
    }
}
