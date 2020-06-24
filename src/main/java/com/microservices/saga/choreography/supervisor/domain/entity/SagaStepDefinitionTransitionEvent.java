package com.microservices.saga.choreography.supervisor.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;

import java.io.Serializable;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SagaStepDefinitionTransitionEvent implements Serializable {
    @Id
    @GeneratedValue
    private Long eventId;

    private String sagaName;

    private String eventName;

    private String failedEventName;

    @StartNode
    private SagaStepDefinition previousStep;

    @EndNode
    private SagaStepDefinition nextStep;

    private Long creationTime;
}
