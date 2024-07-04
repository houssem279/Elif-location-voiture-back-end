package com.houssem.location_voiture.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.houssem.location_voiture.dtos.SignupRequest;
import com.houssem.location_voiture.dtos.UserDto;
import com.houssem.location_voiture.entities.User;
import com.houssem.location_voiture.enums.UserRole;
import com.houssem.location_voiture.repositories.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {
    
    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
       User adminAccount=  userRepository.findByUserRole(UserRole.ADMIN);
       if (adminAccount == null) {
        User newAdminAccount = new User();
        newAdminAccount.setName("admin");
        newAdminAccount.setUserRole(UserRole.ADMIN);
        newAdminAccount.setEmail("admin@test.com");
        newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
        userRepository.save(newAdminAccount);

        
       }
    }

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createCustomer=userRepository.save(user);
        UserDto createUserDto = new UserDto();
        createUserDto.setId(createCustomer.getId());
        createUserDto.setName(createCustomer.getName());
        createUserDto.setEmail(createCustomer.getEmail());
        createUserDto.setUserRole(createCustomer.getUserRole());

        return createUserDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
      
    }
    
}