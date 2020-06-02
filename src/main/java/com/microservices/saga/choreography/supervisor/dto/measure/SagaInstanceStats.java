package com.microservices.saga.choreography.supervisor.dto.measure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutablePair;

@Getter
@Setter
@Builder
public class SagaInstanceStats {
    private final ImmutablePair<Long, Long> maxStepExecutionTime;
    private final Long averageStepExecutionTime;
    private final Long totalExecutionTime;
    private final Integer numberOfSteps;
    private final Integer numberOfFailedSteps;
    private final Integer numberOfSucceedSteps;
}
