package com.microservices.choreographyorchestrator.domain.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KafkaCompensation {
    private String topicName;
}
