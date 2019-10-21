package com.microservices.choreographyorchestrator.dto.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaCompensationInfoDto {
    private String topicName;
}
