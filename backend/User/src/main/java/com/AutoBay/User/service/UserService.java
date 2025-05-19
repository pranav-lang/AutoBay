package com.AutoBay.User.service;

import com.AutoBay.User.dto.UserRequest;
import com.AutoBay.User.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest user);
    UserResponse getUserById(int employeeId);
    List<UserResponse> getAllUsers();
    String getVehicleId(int employeeId);

}
