package com.easc01.disastermediaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DisasterMediaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisasterMediaApiApplication.class, args);
	}

}
