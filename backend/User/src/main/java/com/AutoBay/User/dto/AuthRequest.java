package com.AutoBay.User.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private int employeeId;
    private String password;

}
