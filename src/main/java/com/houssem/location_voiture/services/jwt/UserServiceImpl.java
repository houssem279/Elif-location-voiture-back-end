package com.houssem.location_voiture.services.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.houssem.location_voiture.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{
    private final UserRepository userRepository ;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            
            @Override
            public UserDetails loadUserByUsername (String username){
                return userRepository.findFirstByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
      
    }
    
}