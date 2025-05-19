package com.AutoBay.User.dto;

import com.AutoBay.User.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private int employeeId;
    private String name;
    private long mobileNo;
    private String email;
    private String vehicleId;
    private Role role;
}
