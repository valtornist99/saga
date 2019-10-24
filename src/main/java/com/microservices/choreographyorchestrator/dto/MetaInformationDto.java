package com.microservices.choreographyorchestrator.dto;

import com.microservices.choreographyorchestrator.dto.meta.CompensationInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.FailExecutionInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.RetryInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.SuccessExecutionInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaInformationDto {
    private String sagaName;

    private String stepName;

    private String previousStep;

    private SuccessExecutionInfoDto successExecutionInfo;

    private FailExecutionInfoDto failExecutionInfo;

    private CompensationInfoDto compensationInfo;

    private RetryInfoDto retryInfo;
}
