package com.microservices.saga.choreography.supervisor.domain;

import com.microservices.saga.choreography.supervisor.dto.EventDto;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class SagaStepInstanceTransitionEventFactory {
    public SagaStepInstanceTransitionEvent createSagaStepInstanceTransitionEventFromEventDto(EventDto eventDto) {
        SagaStepInstanceTransitionEvent sagaStepInstanceTransitionEvent = new SagaStepInstanceTransitionEvent();
        sagaStepInstanceTransitionEvent.setEventId(eventDto.getEventId());
        sagaStepInstanceTransitionEvent.setEventName(eventDto.getEventName());
        sagaStepInstanceTransitionEvent.setSagaInstanceId(eventDto.getSagaInstanceId());
        sagaStepInstanceTransitionEvent.setSagaName(eventDto.getSagaName());
        sagaStepInstanceTransitionEvent.setCreationTime(ZonedDateTime.now().toInstant().toEpochMilli());
        return sagaStepInstanceTransitionEvent;
    }
}
