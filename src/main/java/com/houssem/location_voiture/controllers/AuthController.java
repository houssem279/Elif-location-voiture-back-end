package com.houssem.location_voiture.controllers;

import java.util.Optional;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houssem.location_voiture.dtos.AuthenticationRequest;
import com.houssem.location_voiture.dtos.AuthenticationResponse;
import com.houssem.location_voiture.dtos.SignupRequest;
import com.houssem.location_voiture.dtos.UserDto;
import com.houssem.location_voiture.entities.User;

import com.houssem.location_voiture.repositories.UserRepository;
import com.houssem.location_voiture.services.AuthService;
import com.houssem.location_voiture.services.jwt.UserService;
import com.houssem.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    
    private final AuthenticationManager authenticationManager ;
    private final UserService userService ;
    private final JwtUtil jwtUtil ;
    private final UserRepository userRepository ;
    private final AuthService authService ;


    @PostMapping("/signup")
    public ResponseEntity<?> createCostomer(@RequestBody SignupRequest signupRequest){
        if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email already exist.Try again with another email!");
        UserDto createUserDto= authService.createCustomer(signupRequest);
        if(createUserDto==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad Request!");
     return ResponseEntity.status(HttpStatus.CREATED).body(createUserDto);
    }


    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException | DisabledException | UsernameNotFoundException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
    
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
    
        if (optionalUser.isPresent()) {
            String jwt = jwtUtil.generateToken(userDetails);
    
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole()); // Assurez-vous que getUserRole() retourne le UserRole correct
    
            return authenticationResponse;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    

    
    
}