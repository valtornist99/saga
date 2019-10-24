package com.microservices.choreographyorchestrator.util;

import com.microservices.choreographyorchestrator.domain.definition.*;
import com.microservices.choreographyorchestrator.dto.definition.*;

public class DtoToDataUtil {
    public static SagaStepDefinition toMetaNode(SagaStepDefinitionDto sagaStepDefinitionDto) {
        SagaStepDefinition sagaStepDefinition = new SagaStepDefinition();
        sagaStepDefinition.setSagaName(sagaStepDefinitionDto.getSagaName());
        sagaStepDefinition.setStepName(sagaStepDefinitionDto.getStepName());
        sagaStepDefinition.setCompensationInfo(getCompensationInfo(sagaStepDefinitionDto.getCompensationInfo()));
        sagaStepDefinition.setRetryInfo(getRetryInfo(sagaStepDefinitionDto.getRetryInfo()));
        sagaStepDefinition.setSuccessExecutionInfo(getExecutionInfo(sagaStepDefinitionDto.getSuccessExecutionInfo()));
        sagaStepDefinition.setFailExecutionInfo(getExecutionInfo(sagaStepDefinitionDto.getFailExecutionInfo()));
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
