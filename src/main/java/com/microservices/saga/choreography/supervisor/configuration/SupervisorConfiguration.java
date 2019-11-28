package com.microservices.saga.choreography.supervisor.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupervisorConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
