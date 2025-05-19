package com.AutoBay.Parking.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.AutoBay.Parking.Models.ParkingBooking;
import com.AutoBay.Parking.Models.ParkingSlot;
import com.AutoBay.Parking.Repository.ParkingRepository;
import com.AutoBay.Parking.Repository.ParkingSlotRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingExpiryJob {
	
	@Autowired
	private ParkingRepository parkingRepo;
	@Autowired
	private ParkingSlotRepository parkingSlotRepo;
	
	@Transactional
	@Scheduled(fixedRate = 60000)
	public void expireUnconfirmedBookings() {
		
		LocalDateTime cutOff = LocalDateTime.now().minusMinutes(5);
		List<ParkingBooking> expiredBookings = parkingRepo.findByBookingConfirmationFalseAndEntryTimeBefore(cutOff);
		
		for(ParkingBooking booking : expiredBookings) {
			
			
			// TODO :: NOTIFY THE USER ABOUT EXPIRED BOOKINIG.
			
			// delete the unconfirmed booking.
			parkingRepo.delete(booking);
			
			// isBooked is false
			ParkingSlot slot = parkingSlotRepo.findById(booking.getSlotId()).get();
			slot.setBooked(false);
			
		}
		
		System.out.println("Expired " + expiredBookings.size() + " Unconfirmed Booking.");
		
	}
	
}
