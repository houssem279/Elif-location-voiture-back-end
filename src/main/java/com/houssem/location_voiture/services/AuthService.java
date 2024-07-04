package com.houssem.location_voiture.services;

import com.houssem.location_voiture.dtos.SignupRequest;
import com.houssem.location_voiture.dtos.UserDto;

public interface AuthService {
    UserDto createCustomer (SignupRequest signupRequest);
    boolean hasCustomerWithEmail(String email);
    
}