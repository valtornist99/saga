package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SagaStepDefinitionTransitionEventRepository extends Neo4jRepository<SagaStepDefinitionTransitionEvent, Long> {
    SagaStepDefinitionTransitionEvent findSagaStepDefinitionTransitionEventBySagaNameAndEventName(String sagaName, String eventName);

    SagaStepDefinitionTransitionEvent findSagaStepDefinitionTransitionEventByEventId(Long eventId);

    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsByEventName(String eventName);

    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaName(String sagaName);
}
