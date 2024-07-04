package com.houssem.location_voiture.dtos;

import com.houssem.location_voiture.enums.UserRole;

import lombok.Data;


@Data
public class AuthenticationResponse {
    private String jwt ;
    private UserRole userRole ;
    private long userId ;
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    

    /**
     * @return String return the jwt
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * @param jwt the jwt to set
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    /**
     * @return long return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

}