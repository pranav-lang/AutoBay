package com.AutoBay.User.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private final String jwt;

    public AuthResponse(String jwt){
        this.jwt = jwt;
    }
}
