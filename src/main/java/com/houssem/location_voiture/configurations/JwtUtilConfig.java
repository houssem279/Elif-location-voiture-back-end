package com.houssem.location_voiture.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.houssem.utils.JwtUtil;

@Configuration
public class JwtUtilConfig {
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
