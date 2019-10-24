package com.microservices.choreographyorchestrator.dto.definition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompensationInfoDto {
    private KafkaCompensationInfoDto kafkaCompensationInfo;
}
