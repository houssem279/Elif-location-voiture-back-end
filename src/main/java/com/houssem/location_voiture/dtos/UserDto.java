package com.houssem.location_voiture.dtos;

import com.houssem.location_voiture.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
     private Long id ;
    private String name ;
    private String email ;
    private String password ;
    private UserRole userRole;
    
}