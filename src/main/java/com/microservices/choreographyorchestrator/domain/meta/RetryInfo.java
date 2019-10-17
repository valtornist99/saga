package com.microservices.choreographyorchestrator.domain.meta;

import lombok.Data;

@Data
public class RetryInfo {
    private int timeout;
    private String timeUnit;
    private int attempts;
    private KafkaRetry kafkaRetry;
}
