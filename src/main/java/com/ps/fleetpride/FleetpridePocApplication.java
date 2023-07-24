package com.ps.fleetpride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FleetpridePocApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetpridePocApplication.class, args);
	}

}
