package com.ecohabitat.ecoaction_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcoactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoactionServiceApplication.class, args);
	}

}
