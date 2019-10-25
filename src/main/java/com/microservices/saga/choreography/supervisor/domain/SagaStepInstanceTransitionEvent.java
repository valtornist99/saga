package com.microservices.saga.choreography.supervisor.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

@Getter
@Setter
@RelationshipEntity(type = "EVENT")
public class SagaStepInstanceTransitionEvent {
    @Id
    @GeneratedValue
    private Long eventId;

    private String sagaName;

    private String eventName;

    @StartNode
    private SagaStepInstance previousStep;

    @EndNode
    private SagaStepInstance nextStep;

    private Long creationTime;
}
