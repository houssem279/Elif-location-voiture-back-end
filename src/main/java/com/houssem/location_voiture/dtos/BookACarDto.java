package com.houssem.location_voiture.dtos;

import java.sql.Date;

import com.houssem.location_voiture.enums.BookCarStatus;
import lombok.Data;

@Data
public class BookACarDto {
    private Long id;
    private Date fromDate; // Timestamp Unix (en millisecondes)
    private Date toDate;   // Timestamp Unix (en millisecondes)
    private Long days;
    private long amount;
    private BookCarStatus bookCarStatus;
    private Long userId;
    private String email;
    private String  username;



    public Long getUserId() {
        return userId;
    }

    
}
