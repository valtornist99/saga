package com.microservices.saga.choreography.supervisor.domain.definition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompensationInfo {
    private KafkaCompensation kafkaCompensation;
}
