package com.houssem.location_voiture.services.jwt.admin;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.houssem.location_voiture.dtos.BookACarDto;
import com.houssem.location_voiture.dtos.CarDto;
import com.houssem.location_voiture.dtos.CarDtoList;
import com.houssem.location_voiture.dtos.SearchCarDto;
import com.houssem.location_voiture.entities.BookACar;
import com.houssem.location_voiture.entities.Car;
import com.houssem.location_voiture.enums.BookCarStatus;
import com.houssem.location_voiture.repositories.BookACarRepository;
import com.houssem.location_voiture.repositories.CarRepository;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImp  implements AdminService{
    private final CarRepository carRepository ;
    private final BookACarRepository bookACarRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException{
        try{
            Car car = new Car();
            car.setName(carDto.getName());
            car.setBrand(carDto.getBrand());
            car.setColor(carDto.getColor());
            car.setPrice(carDto.getPrice());
            car.setType(carDto.getType());
            car.setDescription(carDto.getDescription());
            car.setModelYear(carDto.getModelYear());
            car.setTransmission(carDto.getTransmission());
            car.setImage(carDto.getImage().getBytes());
            carRepository.save(car);
            return true ;
         

        }catch(Exception e){
            return false;
        }
        
        
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
      
    }

    @Override
    public CarDto getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
    
        return optionalCar.map(Car::getCarDto).orElse(null);
       
    }

    @Override
    public boolean updateCar(Long carId, CarDto carDto) throws IOException {
        try {
            Car car = carRepository.findById(carId).orElseThrow();
            car.setName(carDto.getName());
            car.setBrand(carDto.getBrand());
            car.setColor(carDto.getColor());
            car.setPrice(carDto.getPrice());
            car.setType(carDto.getType());
            car.setDescription(carDto.getDescription());
            car.setModelYear(carDto.getModelYear());
            car.setTransmission(carDto.getTransmission());
            if (carDto.getImage()!= null) {
                car.setImage(carDto.getImage().getBytes());
            }
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<BookACarDto> getBookings() {
        return bookACarRepository.findAll().stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
        
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookACar> optionalBookACar = bookACarRepository.findById(bookingId);
        if (optionalBookACar.isPresent()) {
            BookACar existingBookACar = optionalBookACar.get();
            if (Objects.equals(status, "Approve")) {
                existingBookACar.setBookCarStatus(BookCarStatus.APPROVED);
            } else {
                existingBookACar.setBookCarStatus(BookCarStatus.REJECTED);
            }
            bookACarRepository.save(existingBookACar);
            return true;
        }
        return false;
    }

    @Override
    public CarDtoList searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());
        
    
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
            .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    
        Example<Car> carExample = Example.of(car, exampleMatcher);
        List<Car> cars = carRepository.findAll(carExample);
        
        CarDtoList carDtoList = new CarDtoList();
        carDtoList.setCarDtoList(cars.stream()
            .map(Car::getCarDto)
            .collect(Collectors.toList()));
    
        return carDtoList;
    }
    
    
}

    
    

      
    
    
