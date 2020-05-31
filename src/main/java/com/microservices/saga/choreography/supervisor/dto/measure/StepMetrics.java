package com.microservices.saga.choreography.supervisor.dto.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StepMetrics {
    Long stepId;
    Long startTime;
    Long endTime;
    String status;
}
