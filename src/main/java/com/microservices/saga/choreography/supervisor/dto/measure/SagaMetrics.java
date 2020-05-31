package com.microservices.saga.choreography.supervisor.dto.measure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SagaMetrics {
    Long sagaId;
    Long startTime;
    Long endTime;
    String status;
    String sagaName;
    List<StepMetrics> stepMetrics;
}
