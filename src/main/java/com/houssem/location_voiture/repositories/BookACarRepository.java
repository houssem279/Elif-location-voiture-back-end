package com.houssem.location_voiture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.houssem.location_voiture.entities.BookACar;

@Repository
public interface BookACarRepository extends JpaRepository <BookACar,Long>{
    List<BookACar> findAllByUserId(Long userId);
    
}