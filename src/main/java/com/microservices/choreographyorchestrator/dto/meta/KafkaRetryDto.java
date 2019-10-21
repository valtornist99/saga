package com.microservices.choreographyorchestrator.dto.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaRetryDto {
    private String topicName;
}
