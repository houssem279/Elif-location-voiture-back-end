package com.houssem.location_voiture.services.customer;

import java.util.List;

import com.houssem.location_voiture.dtos.BookACarDto;
import com.houssem.location_voiture.dtos.CarDto;
import com.houssem.location_voiture.dtos.CarDtoList;
import com.houssem.location_voiture.dtos.SearchCarDto;

public interface CustomerService {

     List<CarDto> getAllCars();
     
     CarDto getCarById(Long carId);

     boolean bookACar(Long carId ,BookACarDto bookACarDto);

     List<BookACarDto> getBookingsByUserId(Long userId );
     
     CarDtoList searchCar (SearchCarDto searchCarDto);

    
}