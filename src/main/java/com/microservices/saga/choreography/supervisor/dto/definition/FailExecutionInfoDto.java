package com.microservices.saga.choreography.supervisor.dto.definition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailExecutionInfoDto {
    KafkaFailExecutionInfoDto kafkaFailExecutionInfo;
}
