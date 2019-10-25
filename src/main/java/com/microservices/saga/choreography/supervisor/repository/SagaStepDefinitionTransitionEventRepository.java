package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SagaStepDefinitionTransitionEventRepository extends Neo4jRepository<SagaStepDefinitionTransitionEvent, Long> {
    SagaStepDefinitionTransitionEvent findSagaStepDefinitionBySagaNameAndEventName(String sagaName, String eventName);

    SagaStepDefinitionTransitionEvent findSagaStepDefinitionByEventId(Long eventId);

    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionsByEventName(String eventName);

    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionsBySagaName(String sagaName);
}
