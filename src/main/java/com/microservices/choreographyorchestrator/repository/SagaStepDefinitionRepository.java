package com.microservices.choreographyorchestrator.repository;

import com.microservices.choreographyorchestrator.domain.SagaStepDefinition;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SagaStepDefinitionRepository extends Neo4jRepository<SagaStepDefinition, Long> {
    SagaStepDefinition findMetaGraphNodeBySagaNameAndStepName(String sagaName, String stepName);

    List<SagaStepDefinition> findMetaNodesByStepName(String stepName);

    List<SagaStepDefinition> findMetaNodesBySagaName(String sagaName);

    List<SagaStepDefinition> findMetaGraphNodesBySagaName(String sagaName);
}
