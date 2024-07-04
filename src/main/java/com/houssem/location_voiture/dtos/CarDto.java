package com.houssem.location_voiture.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CarDto {
    private long id;
    private String name;
    private String color;
    private String transmission;
    private String brand;
    private String type;
    
    @Min(1886) // L'année de fabrication de la première voiture
    @Max(2100) // Une limite raisonnable pour les années futures
    private int modelYear;
    
    private String description;
    private Integer price;
    private MultipartFile image;
    private byte[] returnedImage;
}
