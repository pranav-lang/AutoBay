package com.AutoBay.User.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UserProfile {
    @Id
    private int employeeId;
    private String name;
    private long mobileNo;
    private String email;
    private String password;
    private String vehicleId;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
