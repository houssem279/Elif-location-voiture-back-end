package com.houssem.location_voiture.controllers;

import com.houssem.location_voiture.dtos.CarDto;
import com.houssem.location_voiture.dtos.SearchCarDto;
import com.houssem.location_voiture.services.jwt.admin.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/car")
    public ResponseEntity<?> postCar(@Valid @ModelAttribute CarDto carDto) {
        boolean success = adminService.postCar(carDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtoList = adminService.getAllCars();
        return ResponseEntity.ok(carDtoList);
    }

    @DeleteMapping("/car/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        adminService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId) {
        CarDto carDto = adminService.getCarById(carId);
        if (carDto != null) {
            return ResponseEntity.ok(carDto);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("car/{carId}")
    public ResponseEntity<?> updateCar(@PathVariable Long carId ,@ModelAttribute CarDto carDto) throws IOException{
       boolean secces= adminService.updateCar(carId,carDto);
       if (secces) {
        return ResponseEntity.ok().body(null);
        
       }else
       return ResponseEntity.notFound().build();
    }
     @GetMapping("/car/bookings")
    public ResponseEntity<?> getBookings(){
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/car/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId ,@PathVariable String status){
        boolean secces=adminService.changeBookingStatus(bookingId, status);
        if (secces) {return ResponseEntity.ok().build();
            
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarDto searchCarDto){
        return ResponseEntity.ok(adminService.searchCar(searchCarDto));
    }
}
