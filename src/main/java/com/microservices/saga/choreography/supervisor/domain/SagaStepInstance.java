package com.microservices.saga.choreography.supervisor.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Getter
@Setter
public class SagaStepInstance {
    @Id
    @GeneratedValue
    private Long id;

    private Long sagaInstanceId;

    private String sagaName;

    private String stepName;

    private Long sagaStepDefinitionId;
}
