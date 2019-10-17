package com.microservices.choreographyorchestrator.domain.meta;

import lombok.Data;

@Data
public class ExecutionInfo {
    String channelName;
    String eventType;
}
