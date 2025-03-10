package com.example1.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class PubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubApplication.class, args);
	}

}
