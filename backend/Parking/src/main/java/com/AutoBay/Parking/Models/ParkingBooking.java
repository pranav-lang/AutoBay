package com.AutoBay.Parking.Models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_booking")
@Data
public class ParkingBooking {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long bookingId;
	 private int employeeId;
	 private int vehicleId;
	 private UUID slotId;
	 private LocalDateTime entryTime;
	 private LocalDateTime exitTime;
	 private boolean isBooked; // 0 or 1
	 private boolean bookingConfirmation ; // 0 or 1
	 
	 
}
