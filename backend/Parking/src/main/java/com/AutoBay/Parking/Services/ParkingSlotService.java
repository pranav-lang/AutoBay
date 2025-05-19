package com.AutoBay.Parking.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AutoBay.Parking.Models.ParkingSlot;
import com.AutoBay.Parking.Repository.ParkingSlotRepository;



@Service
public class ParkingSlotService {
    
	@Autowired
	private ParkingSlotRepository ParkingSlotRepo;
	
	
	public List<ParkingSlot> GetAllSlots() {
		
		List<ParkingSlot> AllSlots = ParkingSlotRepo.findAll();
		return AllSlots;
	}
	public List<ParkingSlot> GetAvailableSlots(int vehicleType) {
		
		List<ParkingSlot> AllSlots = GetAllSlots();
		
		List<ParkingSlot> AvailableSlots = AllSlots.stream()
                .filter(e -> e.getSlotType() == vehicleType)		    
				.filter(e -> e.isBooked() == false)
		        .collect(Collectors.toList());
		
		return AvailableSlots; 
		
	}
	public boolean checkAvailability(UUID slotId) {
		// TODO Auto-generated method stub
		ParkingSlot Slot  = ParkingSlotRepo.findById(slotId).get();
        return Slot.isBooked();
		
	}
	
	

}
