package com.microservices.saga.choreography.supervisor.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Event {
    private final Long eventId;

    private final Long sagaInstanceId;

    private final String eventName;

    private final String sagaName;
}
