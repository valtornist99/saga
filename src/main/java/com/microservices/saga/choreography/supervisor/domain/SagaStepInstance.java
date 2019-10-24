package com.microservices.saga.choreography.supervisor.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Getter
@Setter
public class SagaStepInstance {
    private Long sagaInstanceId;

    private String sagaName;

    private Long sagaStepDefinitionId;
}
