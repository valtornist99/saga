package com.microservices.saga.choreography.supervisor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.kafka.common.header.Headers;

/**
 * Message from kafka topics
 */
@Getter
@AllArgsConstructor
public class Message {
    private final Headers headers;
    private final String topic;
    private final String eventMessage;
}
