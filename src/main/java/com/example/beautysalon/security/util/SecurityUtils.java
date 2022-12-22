package com.example.beautysalon.security.util;

import com.example.beautysalon.model.BeautyUser;
import com.example.beautysalon.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {
    private static String jwtToken;

    public static UUID getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return customUserDetails.getId();
    }

    public static BeautyUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return customUserDetails.getUser();
    }

    public static void setJwtToken(String jwtToken) {
        SecurityUtils.jwtToken = jwtToken;
    }

    public static String getJwtToken() {
        return SecurityUtils.jwtToken;
    }
}
