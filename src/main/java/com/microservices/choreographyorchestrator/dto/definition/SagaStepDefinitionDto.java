package com.microservices.choreographyorchestrator.dto.definition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SagaStepDefinitionDto {
    private String sagaName;

    private String stepName;

    private String previousStep;

    private SuccessExecutionInfoDto successExecutionInfo;

    private FailExecutionInfoDto failExecutionInfo;

    private CompensationInfoDto compensationInfo;

    private RetryInfoDto retryInfo;
}
