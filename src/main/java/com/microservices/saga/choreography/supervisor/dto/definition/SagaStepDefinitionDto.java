package com.microservices.saga.choreography.supervisor.dto.definition;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SagaStepDefinitionDto {
    private String sagaName;

    private String stepName;

    private List<String> previousSteps;

    private SuccessExecutionInfoDto successExecutionInfo;

    private FailExecutionInfoDto failExecutionInfo;

    private CompensationInfoDto compensationInfo;
}
