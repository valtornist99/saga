package com.microservices.saga.choreography.supervisor.domain.definition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaFailExecutionInfo {
    private String channelName;

    private String eventType;
}
