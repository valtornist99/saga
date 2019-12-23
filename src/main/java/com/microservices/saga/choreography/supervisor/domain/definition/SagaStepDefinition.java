package com.microservices.saga.choreography.supervisor.domain.definition;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.NonNull;
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

    @NonNull
    private String sagaName;

    @NonNull
    private String stepName;

    private SuccessExecutionInfo successExecutionInfo;

    private FailExecutionInfo failExecutionInfo;

    private CompensationInfo compensationInfo;

    private RetryInfo retryInfo;

    public void update(SagaStepDefinition stepDefinition) {
        this.sagaName = stepDefinition.getSagaName();
        this.stepName = stepDefinition.getStepName();
        this.successExecutionInfo = stepDefinition.getSuccessExecutionInfo();
        this.failExecutionInfo = stepDefinition.getFailExecutionInfo();
        this.compensationInfo = stepDefinition.getCompensationInfo();
        this.retryInfo = stepDefinition.getRetryInfo();
    }
}


