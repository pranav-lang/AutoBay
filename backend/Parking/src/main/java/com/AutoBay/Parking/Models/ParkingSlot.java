package com.AutoBay.Parking.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ParkingSlot {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID slotId;
   private int  slotNumber;
   private int zoneId; 
   private int slotType;
   private boolean  isBooked; // 0 or 1
}
