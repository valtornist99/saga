package com.microservices.choreographyorchestrator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservices.choreographyorchestrator.dto.meta.CompensationInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.FailExecutionInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.RetryInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.SuccessExecutionInfoDto;
import lombok.Data;

@Data
public class MetaInformationDto {
    @JsonProperty(value = "saga_name")
    private String sagaName;
    @JsonProperty(value = "step_name")
    private String stepName;
    @JsonProperty(value = "previous_step")
    private String previousStep;
    @JsonProperty(value = "success_execution_info")
    private SuccessExecutionInfoDto successExecutionInfo;
    @JsonProperty(value = "fail_execution_info")
    private FailExecutionInfoDto failExecutionInfo;
    @JsonProperty(value = "compensation_info")
    private CompensationInfoDto compensationInfo;
    @JsonProperty(value = "retry_info")
    private RetryInfoDto retryInfo;
}
