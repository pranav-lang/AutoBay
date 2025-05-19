package com.AutoBay.Parking.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AutoBay.Parking.Models.ParkingBooking;
import com.AutoBay.Parking.Services.ParkingService;
import com.AutoBay.Parking.Services.ParkingSlotService;
import com.AutoBay.Parking.dto.EmployeeDto;
import com.AutoBay.Parking.dto.ResponseDto;

@RestController
@RequestMapping("/api/bookings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping("/book-slot")
    public ResponseEntity<?> bookSlot(@RequestBody EmployeeDto employee, @RequestParam UUID slotId) {
        boolean isBooked = parkingSlotService.checkAvailability(slotId);

        if (isBooked) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Slot is not available.");
        }

        ParkingBooking booking = parkingService.bookSlot(employee, slotId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Booking ID: " + booking.getBookingId() + " confirmed.", booking));
    }

    @PutMapping("/{bookingId}/confirm")
    public ResponseEntity<?> confirmBooking(@PathVariable Long bookingId) {
        try {
            ParkingBooking booking = parkingService.confirmBooking(bookingId);
            return ResponseEntity.ok("Booking ID: " + booking.getBookingId() + " confirmed.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long bookingId) {
        try {
            ParkingBooking booking = parkingService.getBookingDetails(bookingId);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        try {
            parkingService.cancelBooking(bookingId);
            return ResponseEntity.ok("Booking ID: " + bookingId + " canceled.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
