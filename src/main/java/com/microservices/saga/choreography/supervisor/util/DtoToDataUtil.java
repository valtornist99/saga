package com.microservices.saga.choreography.supervisor.util;

import com.microservices.saga.choreography.supervisor.domain.definition.*;
import com.microservices.saga.choreography.supervisor.dto.definition.*;

public class DtoToDataUtil {
    public static SagaStepDefinition toMetaNode(SagaStepDefinitionDto sagaStepDefinitionDto) {
        SagaStepDefinition sagaStepDefinition = new SagaStepDefinition();
        sagaStepDefinition.setSagaName(sagaStepDefinitionDto.getSagaName());
        sagaStepDefinition.setStepName(sagaStepDefinitionDto.getStepName());
        sagaStepDefinition.setCompensationInfo(getCompensationInfo(sagaStepDefinitionDto.getCompensationInfo()));
        sagaStepDefinition.setRetryInfo(getRetryInfo(sagaStepDefinitionDto.getRetryInfo()));
        sagaStepDefinition.setSuccessExecutionInfo(getSuccessExecutionInfo(sagaStepDefinitionDto.getSuccessExecutionInfo()));
        sagaStepDefinition.setFailExecutionInfo(getFailExecutionInfo(sagaStepDefinitionDto.getFailExecutionInfo()));
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

    private static SuccessExecutionInfo getSuccessExecutionInfo(SuccessExecutionInfoDto successExecutionInfoDto) {
        SuccessExecutionInfo successExecutionInfo = new SuccessExecutionInfo();
        KafkaSuccessExecutionInfo kafkaSuccessExecutionInfo = new KafkaSuccessExecutionInfo();
        kafkaSuccessExecutionInfo.setChannelName(successExecutionInfoDto.getKafkaSuccessExecutionInfoDto().getChannelName());
        kafkaSuccessExecutionInfo.setEventType(successExecutionInfoDto.getKafkaSuccessExecutionInfoDto().getEventType());
        successExecutionInfo.setKafkaSuccessExecutionInfo(kafkaSuccessExecutionInfo);
        return successExecutionInfo;
    }

    private static FailExecutionInfo getFailExecutionInfo(FailExecutionInfoDto failExecutionInfoDto) {
        FailExecutionInfo failExecutionInfo = new FailExecutionInfo();
        KafkaFailExecutionInfo kafkaFailExecutionInfo = new KafkaFailExecutionInfo();
        kafkaFailExecutionInfo.setChannelName(failExecutionInfoDto.getKafkaFailExecutionInfoDto().getChannelName());
        kafkaFailExecutionInfo.setEventType(failExecutionInfoDto.getKafkaFailExecutionInfoDto().getEventType());
        failExecutionInfo.setKafkaFailExecutionInfo(kafkaFailExecutionInfo);
        return failExecutionInfo;
    }
}
