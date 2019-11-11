package com.microservices.saga.choreography.supervisor.domain.definition;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Getter
@Setter
public class SagaStepDefinition {
    @Id
    @GeneratedValue
    private Long id;

    private String sagaName;

    private String stepName;

    private SuccessExecutionInfo successExecutionInfo;

    private FailExecutionInfo failExecutionInfo;

    private CompensationInfo compensationInfo;

    private RetryInfo retryInfo;
}


