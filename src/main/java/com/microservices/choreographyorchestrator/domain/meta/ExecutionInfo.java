package com.microservices.choreographyorchestrator.domain.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExecutionInfo {
    private String channelName;

    private String eventType;
}
