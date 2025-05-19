package com.AutoBay.User.controller;

import com.AutoBay.User.dto.AuthRequest;
import com.AutoBay.User.dto.AuthResponse;
import com.AutoBay.User.dto.UserRequest;
import com.AutoBay.User.dto.UserResponse;
import com.AutoBay.User.security.CustomUserDetailsService;
import com.AutoBay.User.security.JwtUtil;
import com.AutoBay.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(String.valueOf(authRequest.getEmployeeId()), authRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect employeeId or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(authRequest.getEmployeeId()));
        final String role = userService.getUserById(authRequest.getEmployeeId()).getRole().name();
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }
}
