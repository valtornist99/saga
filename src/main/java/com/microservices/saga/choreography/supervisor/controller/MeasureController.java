package com.microservices.saga.choreography.supervisor.controller;

import com.microservices.saga.choreography.supervisor.dto.measure.SagaInstanceStats;
import com.microservices.saga.choreography.supervisor.dto.measure.SagaMetrics;
import com.microservices.saga.choreography.supervisor.dto.measure.SagaStats;
import com.microservices.saga.choreography.supervisor.dto.measure.StepMetrics;
import com.microservices.saga.choreography.supervisor.exception.StepDefinitionNotFoundException;
import com.microservices.saga.choreography.supervisor.service.MeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measure")
@RequiredArgsConstructor
public class MeasureController {
    private final MeasureService measureService;

    @GetMapping("/step")
    public List<StepMetrics> getAllStepMetrics() {
        return measureService.getAllStepMetrics();
    }

    @GetMapping("/step/{stepId}")
    public StepMetrics getStepMetricById(@PathVariable Long stepId) throws StepDefinitionNotFoundException {
        return measureService.getStepMetricById(stepId);
    }

    @GetMapping("/saga")
    public List<SagaMetrics> getAllSagaMetrics() {
        return measureService.getAllSagaMetrics();
    }

    @GetMapping("/saga/{sagaId}")
    public SagaMetrics getSagaMetricById(@PathVariable Long sagaId) throws StepDefinitionNotFoundException {
        return measureService.getSagaMetricByInstance(sagaId);
    }

    @GetMapping("/saga/stats/{sagaName}")
    public SagaStats getStatisticsForSaga(@PathVariable String sagaName) {
        return measureService.getStatsForSaga(sagaName);
    }

    @GetMapping("/saga/stats/instance/{sagaId}")
    public SagaInstanceStats getStatisticForSagaInstance(@PathVariable Long sagaId) throws StepDefinitionNotFoundException {
        return measureService.getStatsForSagaInstance(sagaId);
    }
}
