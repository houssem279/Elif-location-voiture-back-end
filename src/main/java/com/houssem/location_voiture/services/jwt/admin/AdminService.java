package com.houssem.location_voiture.services.jwt.admin;

import java.io.IOException;
import java.util.List;

import com.houssem.location_voiture.dtos.BookACarDto;
import com.houssem.location_voiture.dtos.CarDto;
import com.houssem.location_voiture.dtos.CarDtoList;
import com.houssem.location_voiture.dtos.SearchCarDto;


public interface AdminService {

    boolean postCar(CarDto carDto);
    
    List<CarDto>getAllCars();

    void deleteCar(Long carId);

    CarDto getCarById(Long carId);

    boolean updateCar(Long carId,CarDto carDto)  throws IOException;

    List<BookACarDto> getBookings( );

    boolean changeBookingStatus(Long bookingId,String status);
    
    CarDtoList searchCar (SearchCarDto searchCarDto);
    
}