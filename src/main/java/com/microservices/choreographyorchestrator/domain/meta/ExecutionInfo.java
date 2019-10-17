package com.microservices.choreographyorchestrator.domain.meta;

import lombok.Data;

@Data
public class ExecutionInfo {
    private String channelName;
    private String eventType;
}
