package com.houssem.location_voiture.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.houssem.location_voiture.dtos.BookACarDto;
import com.houssem.location_voiture.dtos.CarDto;
import com.houssem.location_voiture.dtos.CarDtoList;
import com.houssem.location_voiture.dtos.SearchCarDto;
import com.houssem.location_voiture.entities.BookACar;
import com.houssem.location_voiture.entities.Car;
import com.houssem.location_voiture.entities.User;
import com.houssem.location_voiture.enums.BookCarStatus;
import com.houssem.location_voiture.repositories.BookACarRepository;
import com.houssem.location_voiture.repositories.CarRepository;
import com.houssem.location_voiture.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CarRepository carRepository ;
    @Autowired
    private final UserRepository userRepository ;
    @Autowired
    private final BookACarRepository bookACarRepository;
    

    @Override
    public List<CarDto> getAllCars() {
     
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }
    @Override
    public CarDto getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
    
        return optionalCar.map(Car::getCarDto).orElse(null);
       
    }
    @Override
    public boolean bookACar(Long carId, BookACarDto bookACarDto) {
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
        Optional<Car> optionalCar = carRepository.findById(carId);
    
        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            BookACar bookACar = new BookACar();
    
            // Calculer le nombre de jours entre fromDate et toDate
            long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
    
            // Définir les propriétés de BookACar
            bookACar.setDays(days);
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(optionalCar.get());
            bookACar.setAmount(optionalCar.get().getPrice() * days);
            bookACar.setFromDate(bookACarDto.getFromDate());
            bookACar.setToDate(bookACarDto.getToDate());
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
    
            // Sauvegarder la réservation
            bookACarRepository.save(bookACar);
            return true;
        }
        return false;
    }
    
    @Override
    public List<BookACarDto> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
        
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