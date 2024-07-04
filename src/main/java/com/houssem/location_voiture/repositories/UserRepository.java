package com.houssem.location_voiture.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.houssem.location_voiture.entities.User;
import com.houssem.location_voiture.enums.UserRole;



@Repository
public interface  UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);
    

    User findByUserRole(UserRole userRole);
    
}