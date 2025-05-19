package com.AutoBay.Parking.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AutoBay.Parking.Models.ParkingSlot;
import com.AutoBay.Parking.Services.ParkingSlotService;


@RestController
@RequestMapping("/api/slots")
public class ParkingSlotsController {

	@Autowired
	private ParkingSlotService parkingSlotService;
	
	// GET Available Slots -  /api/bookings GET
	@GetMapping("/")
	private ResponseEntity<?> GetAvailableSolts(@RequestParam int vehicleType) {
	    List<ParkingSlot> availableSlots = parkingSlotService.GetAvailableSlots(vehicleType);

	    if (availableSlots.isEmpty()) {
	        return ResponseEntity.noContent().build(); // HTTP 204 No Content
	    }

	    return ResponseEntity.ok(availableSlots); // HTTP 200 with list
	}

}
