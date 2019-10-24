package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.SagaStepTransitionEvent;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SagaStepTransitionRepository extends Neo4jRepository<SagaStepTransitionEvent, Long> {
    List<SagaStepTransitionEvent> findSagaStepTransitionEventsByPreviousStep(SagaStepDefinition previousStep);

    List<SagaStepTransitionEvent> findSagaStepTransitionEventsByNextStep(SagaStepDefinition nextStep);

    List<SagaStepTransitionEvent> findSagaStepTransitionEventsByPreviousStepAndNextStep(SagaStepDefinition previousStep, SagaStepDefinition nextStep);
}
