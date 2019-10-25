package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.SagaStepInstance;
import com.microservices.saga.choreography.supervisor.domain.SagaStepInstanceTransitionEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SagaStepInstanceTransitionRepository extends Neo4jRepository<SagaStepInstanceTransitionEvent, Long> {
    List<SagaStepInstanceTransitionEvent> findSagaStepTransitionEventsByPreviousStep(SagaStepInstance previousStep);

    List<SagaStepInstanceTransitionEvent> findSagaStepTransitionEventsByNextStep(SagaStepInstance nextStep);

    List<SagaStepInstanceTransitionEvent> findSagaStepTransitionEventsByPreviousStepAndNextStep(SagaStepInstance previousStep, SagaStepInstance nextStep);
}
