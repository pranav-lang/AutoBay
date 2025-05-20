package com.AutoBay.Parking.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AutoBay.Parking.Models.ParkingZone;
import com.AutoBay.Parking.Repository.ParkingZoneRepository;


@Service
public class ParkingZoneService {
	
	@Autowired
	private ParkingZoneRepository parkingZoneRepo;

	public List<ParkingZone> getAllZones() {
		// TODO Auto-generated method stub
		List<ParkingZone> allZones = parkingZoneRepo.findAll();
		return allZones;
	}

}
