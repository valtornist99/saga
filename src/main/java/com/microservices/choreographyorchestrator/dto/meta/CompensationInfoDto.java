package com.microservices.choreographyorchestrator.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompensationInfoDto {
    @JsonProperty(value = "kafka_compensation")
    private KafkaCompensationInfoDto kafkaCompensationInfoDto;
}
