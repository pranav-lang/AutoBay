package com.AutoBay.Parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AutoBay.Parking.Models.ParkingZone;

@Repository
public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Long>{
    
}
