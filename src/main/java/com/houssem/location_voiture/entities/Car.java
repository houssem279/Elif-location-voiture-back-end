package com.houssem.location_voiture.entities;



import com.houssem.location_voiture.dtos.CarDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name ;
    private String color ;
    private String transmission ;
    private String brand ;
    private String type ;
     @Min(1886) // L'année de fabrication de la première voiture
    @Max(2100) // Une limite raisonnable pour les années futures
    private int modelYear;
    private String description ;
    private Integer price ;
    @Column(columnDefinition = "longblob")
    private byte[] image ;

    public CarDto getCarDto(){
        CarDto carDto = new CarDto();
        carDto.setId(id);
        carDto.setName(name);
        carDto.setDescription(description);
        carDto.setColor(color);
        carDto.setType(type);
        carDto.setPrice(price);
        carDto.setTransmission(transmission);
        carDto.setModelYear(modelYear);
        carDto.setBrand(brand);
        carDto.setReturnedImage(image);
        return carDto;

        

    }
}