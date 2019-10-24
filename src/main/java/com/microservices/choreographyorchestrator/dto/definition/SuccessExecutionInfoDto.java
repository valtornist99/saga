package com.microservices.choreographyorchestrator.dto.definition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessExecutionInfoDto {
    private String channelName;

    private String eventType;
}
