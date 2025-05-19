package com.AutoBay.Parking.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AutoBay.Parking.Models.ParkingBooking;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingBooking, Long> {
    List<ParkingBooking> findByBookingConfirmationFalseAndEntryTimeBefore(LocalDateTime cutOff);
}