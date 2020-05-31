package com.microservices.saga.choreography.supervisor.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Getter
@Setter
@Builder(toBuilder = true)
public class SagaStepInstance {
    @Id
    @GeneratedValue
    private Long id;

    private Long sagaInstanceId;

    private String sagaName;

    private String stepName;

    private Long sagaStepDefinitionId;

    private StepStatus stepStatus;
}
