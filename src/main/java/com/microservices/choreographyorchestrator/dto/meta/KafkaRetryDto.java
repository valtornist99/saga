package com.microservices.choreographyorchestrator.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KafkaRetryDto {
    @JsonProperty(value = "topic_name")
    private String topicName;
}
