package com.microservices.choreographyorchestrator.domain.meta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaRetry {
    private String topicName;
}
