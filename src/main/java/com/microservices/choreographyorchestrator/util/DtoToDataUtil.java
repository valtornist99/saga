package com.microservices.choreographyorchestrator.util;

import com.microservices.choreographyorchestrator.domain.SagaStepDefinition;
import com.microservices.choreographyorchestrator.domain.meta.*;
import com.microservices.choreographyorchestrator.dto.MetaInformationDto;
import com.microservices.choreographyorchestrator.dto.meta.CompensationInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.FailExecutionInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.RetryInfoDto;
import com.microservices.choreographyorchestrator.dto.meta.SuccessExecutionInfoDto;

public class DtoToDataUtil {
    public static SagaStepDefinition toMetaNode(MetaInformationDto metaInformationDto) {
        SagaStepDefinition sagaStepDefinition = new SagaStepDefinition();
        sagaStepDefinition.setSagaName(metaInformationDto.getSagaName());
        sagaStepDefinition.setStepName(metaInformationDto.getStepName());
        sagaStepDefinition.setCompensationInfo(getCompensationInfo(metaInformationDto.getCompensationInfo()));
        sagaStepDefinition.setRetryInfo(getRetryInfo(metaInformationDto.getRetryInfo()));
        sagaStepDefinition.setSuccessExecutionInfo(getExecutionInfo(metaInformationDto.getSuccessExecutionInfo()));
        sagaStepDefinition.setFailExecutionInfo(getExecutionInfo(metaInformationDto.getFailExecutionInfo()));
        return sagaStepDefinition;
    }

    private static CompensationInfo getCompensationInfo(CompensationInfoDto compensationInfoDto) {
        CompensationInfo compensationInfo = new CompensationInfo();
        String topicName = compensationInfoDto.getKafkaCompensationInfo().getTopicName();
        compensationInfo.setKafkaCompensation(new KafkaCompensation(topicName));
        return compensationInfo;
    }

    private static RetryInfo getRetryInfo(RetryInfoDto retryInfoDto) {
        RetryInfo retryInfo = new RetryInfo();
        retryInfo.setTimeout(retryInfoDto.getTimeout());
        retryInfo.setAttempts(retryInfoDto.getAttempts());
        retryInfo.setTimeUnit(retryInfoDto.getTimeUnit());
        String topicName = retryInfo.getKafkaRetry().getTopicName();
        retryInfo.setKafkaRetry(new KafkaRetry(topicName));
        return retryInfo;
    }

    private static ExecutionInfo getExecutionInfo(SuccessExecutionInfoDto executionInfoDto) {
        ExecutionInfo executionInfo = new ExecutionInfo();
        executionInfo.setChannelName(executionInfoDto.getChannelName());
        executionInfo.setEventType(executionInfoDto.getEventType());
        return executionInfo;
    }

    private static ExecutionInfo getExecutionInfo(FailExecutionInfoDto executionInfoDto) {
        ExecutionInfo executionInfo = new ExecutionInfo();
        executionInfo.setChannelName(executionInfoDto.getChannelName());
        executionInfo.setEventType(executionInfoDto.getEventType());
        return executionInfo;
    }
}
