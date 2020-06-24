package com.microservices.saga.choreography.supervisor.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Getter
@Setter
@Builder(toBuilder = true)
@RelationshipEntity(type = "EVENT")
@AllArgsConstructor
@NoArgsConstructor
public class SagaStepInstanceTransitionEvent {
    @Id
    @GeneratedValue
    private Long id;

    private Long eventId;

    private String sagaName;

    private String eventName;

    private Long sagaInstanceId;

    @StartNode
    private SagaStepInstance previousStep;

    @EndNode
    private SagaStepInstance nextStep;

    private Long creationTime;
}
