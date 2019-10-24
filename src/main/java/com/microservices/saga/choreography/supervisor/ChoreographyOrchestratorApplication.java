package com.microservices.saga.choreography.supervisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChoreographyOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoreographyOrchestratorApplication.class, args);
	}

}
