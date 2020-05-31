package com.microservices.saga.choreography.supervisor.service;

import com.microservices.saga.choreography.supervisor.domain.Event;
import com.microservices.saga.choreography.supervisor.service.compensation.CompensationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventHandler {
    private final CompensationService compensationService;
//    private final GraphService graphService;

    public void handle(Event event) {
//        if (graphService.isEventSuccessful(event)) {
//            compensationService.compensate(event.getSagaInstanceId());
//        }
//        graphService.handleSagaInstanceEvent(event);
    }
}
