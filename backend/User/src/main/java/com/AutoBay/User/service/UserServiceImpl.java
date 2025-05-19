package com.AutoBay.User.service;

import com.AutoBay.User.dto.UserRequest;
import com.AutoBay.User.dto.UserResponse;
import com.AutoBay.User.entity.UserProfile;
import com.AutoBay.User.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse registerUser(UserRequest request) {
        UserProfile user = new UserProfile();
        BeanUtils.copyProperties(request, user);
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;

    }

    @Override
    public UserResponse getUserById(int employeeId) {
        UserProfile user = userRepository
                .findById(employeeId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserResponse res = new UserResponse();
                    BeanUtils.copyProperties(user, res);
                    return res;
                }).collect(Collectors.toList());

    }

    @Override
    public String getVehicleId(int employeeId) {
        return userRepository.findById(employeeId)
                .map(UserProfile::getVehicleId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}
