package com.AutoBay.Parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.AutoBay.Parking.Models.VehicleType;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
		System.out.println(VehicleType.Car);
		
	}

}
