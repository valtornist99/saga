package com.microservices.choreographyorchestrator.domain.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompensationInfo {
    private KafkaCompensation kafkaCompensation;
}
