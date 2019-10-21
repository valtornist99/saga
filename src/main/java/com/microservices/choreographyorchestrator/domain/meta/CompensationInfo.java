package com.microservices.choreographyorchestrator.domain.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompensationInfo {
    private KafkaCompensation kafkaCompensation;
}
