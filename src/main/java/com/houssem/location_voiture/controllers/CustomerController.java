package com.houssem.location_voiture.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houssem.location_voiture.dtos.BookACarDto;
import com.houssem.location_voiture.dtos.CarDto;
import com.houssem.location_voiture.dtos.SearchCarDto;
import com.houssem.location_voiture.services.customer.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService ;
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtoList = customerService.getAllCars();
        return ResponseEntity.ok(carDtoList);
    }
    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId) {
        CarDto carDto = customerService.getCarById(carId);
        if (carDto != null) {
            return ResponseEntity.ok(carDto);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/car/book/{carId}")
    public ResponseEntity<?> bookACar(@PathVariable Long carId,@RequestBody BookACarDto bookACarDto){
       boolean secces= customerService.bookACar(carId, bookACarDto);
       if (secces) {
        return ResponseEntity.ok().build();
        
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("/car/bookings/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }
    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarDto searchCarDto){
        return ResponseEntity.ok(customerService.searchCar(searchCarDto));
    }
    
}