package com.microservices.saga.choreography.supervisor.dto.measure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Map;

@Getter
@Setter
@Builder
public class SagaStats {
    private final Map<Long, SagaInstanceStats> instanceStatistic;
    private final ImmutablePair<Long, Long> mostTimeConsumingSaga;
    private final Long averageTimeConsumingSaga;
}
