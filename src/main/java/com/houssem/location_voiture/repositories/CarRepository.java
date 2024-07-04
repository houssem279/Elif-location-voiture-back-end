package com.houssem.location_voiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.houssem.location_voiture.entities.Car;
 @Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    
}