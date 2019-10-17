package com.microservices.choreographyorchestrator.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RetryInfoDto {
    private Integer timeout;
    private String timeunit;
    private Integer attempts;
    @JsonProperty(value = "kafka_retry")
    private KafkaRetryDto kafkaRetry;
}
