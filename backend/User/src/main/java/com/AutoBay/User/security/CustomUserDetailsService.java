package com.AutoBay.User.security;

import com.AutoBay.User.entity.UserProfile;
import com.AutoBay.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
        UserProfile user = userRepository.findById(Integer.parseInt(employeeId))
                .orElseThrow( () -> new UsernameNotFoundException("User not found with employeeId: " + employeeId ));
        return User.builder()
                .username(String.valueOf(user.getEmployeeId()))
                .password(user.getPassword())
                .roles(user
                        .getRole()
                        .name())
                .build();
    }
}
