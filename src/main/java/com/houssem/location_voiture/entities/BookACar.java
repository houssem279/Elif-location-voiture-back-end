package com.houssem.location_voiture.entities;

import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.houssem.location_voiture.dtos.BookACarDto;
import com.houssem.location_voiture.enums.BookCarStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class BookACar {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id ;
    private Date fromDate;
    private Date toDate ;
    private Long days ;
    private long amount ;
    private BookCarStatus bookCarStatus;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action =OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user ;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action =OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car ;
    public BookACarDto getBookACarDto(){
        BookACarDto bookACarDto= new BookACarDto();
        bookACarDto.setId(id);
        bookACarDto.setFromDate(fromDate);
        bookACarDto.setToDate(toDate);
        bookACarDto.setDays(days);
        bookACarDto.setAmount(amount);
        bookACarDto.setBookCarStatus(bookCarStatus);
        bookACarDto.setEmail(user.getEmail());
        bookACarDto.setUsername(user.getName());
        bookACarDto.setUserId(user.getId());
        return bookACarDto;
    }



    
    
}