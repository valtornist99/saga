package com.microservices.saga.choreography.supervisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
@EntityScan("com.microservices.saga.choreography.supervisor.domain")
public class ChoreographyOrchestratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoreographyOrchestratorApplication.class, args);
    }

}
