package com.microservices.saga.choreography.supervisor.domain.definition;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;

public class SagaStepDefinitionTransitionEvent {
    @Id
    @GeneratedValue
    private Long eventId;

    private String sagaName;

    private String eventName;

    @StartNode
    private SagaStepDefinition previousStep;

    @EndNode
    private SagaStepDefinition nextStep;

    private Long creationTime;
}
