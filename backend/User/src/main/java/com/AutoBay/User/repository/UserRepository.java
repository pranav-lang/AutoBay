package com.AutoBay.User.repository;


import com.AutoBay.User.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile, Integer> {

}
