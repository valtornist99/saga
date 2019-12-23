package com.microservices.saga.choreography.supervisor.domain.definition;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@Getter
@Setter
public class RetryInfo {
    @Id
    @GeneratedValue
    private Long id;

    private int timeout;

    private String timeUnit;

    private int attempts;

    private KafkaRetry kafkaRetry;
}
