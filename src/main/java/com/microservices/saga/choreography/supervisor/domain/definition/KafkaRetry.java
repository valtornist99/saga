package com.microservices.saga.choreography.supervisor.domain.definition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaRetry {
    @Id
    @GeneratedValue
    private Long id;

    private String topicName;
}
