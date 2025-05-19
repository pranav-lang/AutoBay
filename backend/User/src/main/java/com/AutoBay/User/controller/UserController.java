package com.AutoBay.User.controller;

import com.AutoBay.User.dto.UserRequest;
import com.AutoBay.User.dto.UserResponse;
import com.AutoBay.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int employeeId){
        return ResponseEntity.ok(userService.getUserById(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{employeeId}/vehicle")
    public ResponseEntity<String> getVehicle(@PathVariable int employeeId){
        return ResponseEntity.ok(userService.getVehicleId(employeeId));
    }
}

