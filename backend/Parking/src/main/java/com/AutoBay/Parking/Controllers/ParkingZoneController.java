package com.AutoBay.Parking.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AutoBay.Parking.Models.ParkingZone;
import com.AutoBay.Parking.Repository.ParkingZoneRepository;
import com.AutoBay.Parking.Services.ParkingZoneService;
import com.AutoBay.Parking.dto.ResponseDto;

@RestController
@RequestMapping("/api/zones")
public class ParkingZoneController {
    
	@Autowired
	private ParkingZoneService parkingZoneService;
	
	
	@GetMapping("/")
	public ResponseEntity<?> GetAllZones() {
		List<ParkingZone> allParkingZones = parkingZoneService.getAllZones();
		return ResponseEntity.ok(new ResponseDto("" , allParkingZones));
	}
	
	
	
}
