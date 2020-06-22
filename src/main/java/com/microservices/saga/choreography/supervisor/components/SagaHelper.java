package com.microservices.saga.choreography.supervisor.components;

import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionRepository;
import com.microservices.saga.choreography.supervisor.SagaMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;


@Component
public class SagaHelper {
    @Autowired
    private SagaStepDefinitionRepository sagaTemplateStepRepository;

    public boolean isFirstStepOfSagaInstance(String sagaName, String stepName) {
        // TODO: consider sort of nodes
        var firstSagaTemplateStep = sagaTemplateStepRepository.findSagaStepDefinitionsBySagaName(sagaName)
                .get(0)
                .getStepName();

        return stepName.equals(firstSagaTemplateStep);
    }

    public boolean isLastStepOfSagaInstance(String sagaName, String stepName) {
        // TODO: consider sort of nodes
        var lastSagaTemplateStep = sagaTemplateStepRepository.findEndNodesBySagaName(sagaName)
                .get(0)
                .getStepName();

        return stepName.equals(lastSagaTemplateStep);
    }

    @PostConstruct
    public void initializeSagaTemplateTotalMetric() {
        var sagaTemplateNames = getSagaTemplateNames();

        for (var sagaTemplateName : sagaTemplateNames) {
            SagaMetrics.incrementSagaTemplate(sagaTemplateName);
        }
    }


    private HashSet<String> getSagaTemplateNames() {
        var sagaTemplateSteps = sagaTemplateStepRepository.findAll();
        var sagaTemplateNames = new HashSet<String>();

        for (var sagaTemplateStep : sagaTemplateSteps) {
            sagaTemplateNames.add(sagaTemplateStep.getSagaName());
        }

        return sagaTemplateNames;
    }
}
