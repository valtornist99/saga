package com.microservices.choreographyorchestrator.repository;

import com.microservices.choreographyorchestrator.domain.SagaStepDefinition;
import com.microservices.choreographyorchestrator.domain.SagaStepTransitionEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SagaStepTransitionRepository extends Neo4jRepository<SagaStepTransitionEvent, Long> {
    List<SagaStepTransitionEvent> findMetaNodeRelationByPreviousStep(SagaStepDefinition previousStep);

    List<SagaStepTransitionEvent> findMetaNodeRelationByNextStep(SagaStepDefinition nextStep);

    List<SagaStepTransitionEvent> findMetaNodeRelationByPreviousStepAndNextStep(SagaStepDefinition previousStep, SagaStepDefinition nextStep);
}
