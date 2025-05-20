package com.AutoBay.Parking.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingZone {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long zoneId;
	private String zoneBuildingNumber;
	private Long zoneFloor; 
	private int zoneOccupancy;
}
