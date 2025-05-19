package com.AutoBay.Parking.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AutoBay.Parking.Models.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, UUID>{

}
