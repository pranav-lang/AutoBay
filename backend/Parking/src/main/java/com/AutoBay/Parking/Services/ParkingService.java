package com.AutoBay.Parking.Services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AutoBay.Parking.Models.ParkingBooking;
import com.AutoBay.Parking.Models.ParkingSlot;
import com.AutoBay.Parking.Repository.ParkingRepository;
import com.AutoBay.Parking.Repository.ParkingSlotRepository;
import com.AutoBay.Parking.dto.EmployeeDto;

import jakarta.transaction.Transactional;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepo;

    @Autowired
    private ParkingSlotRepository parkingSlotRepo;

    @Transactional
    public ParkingBooking bookSlot(EmployeeDto employee, UUID slotId) {
        ParkingSlot parkingSlot = parkingSlotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (parkingSlot.isBooked()) {
            throw new RuntimeException("Slot is already booked");
        }

        ParkingBooking booking = new ParkingBooking();
        booking.setEmployeeId(employee.getEmployeeId());
        booking.setVehicleId(employee.getVehicleId());
        booking.setSlotId(slotId);
        booking.setEntryTime(LocalDateTime.now());
        booking.setBooked(true);
        booking.setBookingConfirmation(false);

        parkingSlot.setBooked(true);

        parkingRepo.save(booking);
        parkingSlotRepo.save(parkingSlot);
        return booking;
    }

    @Transactional
    public ParkingBooking confirmBooking(Long bookingId) {
        ParkingBooking booking = parkingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.isBookingConfirmation()) {
            throw new RuntimeException("Booking already confirmed");
        }

        booking.setBookingConfirmation(true);
        return parkingRepo.save(booking);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        ParkingBooking booking = parkingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        ParkingSlot slot = parkingSlotRepo.findById(booking.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found for cancellation"));

        slot.setBooked(false);
        parkingSlotRepo.save(slot);
        parkingRepo.delete(booking);
    }

    public ParkingBooking getBookingDetails(Long bookingId) {
        return parkingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}
