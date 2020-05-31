package com.microservices.saga.choreography.supervisor.service;

import com.microservices.saga.choreography.supervisor.domain.Event;
import com.microservices.saga.choreography.supervisor.domain.SagaStepInstance;
import com.microservices.saga.choreography.supervisor.domain.SagaStepInstanceTransitionEvent;
import com.microservices.saga.choreography.supervisor.domain.StepStatus;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionTransitionEventRepository;
import com.microservices.saga.choreography.supervisor.repository.SagaStepInstanceRepository;
import com.microservices.saga.choreography.supervisor.repository.SagaStepInstanceTransitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * Service that responsible for handling new instance transition events
 */
@Service
@AllArgsConstructor
public class InstanceService {
    private final SagaStepDefinitionTransitionEventRepository eventDefinitionRepository;
    private final SagaStepInstanceRepository sagaStepInstanceRepository;
    private final SagaStepInstanceTransitionRepository eventInstanceRepository;

    /**
     * Handling new event
     *
     * @param event event
     */
    public void handleEvent(Event event) {
        SagaStepDefinitionTransitionEvent eventDefinition = getEventDefinition(event);

        SagaStepDefinition stepDefinition = eventDefinition.getPreviousStep();
        SagaStepInstance occurredStep = getOccurredSagaStepInstanceWithSuccessfulStatus(event, stepDefinition);

        SagaStepInstance nextStep = SagaStepInstance.builder()
                .stepStatus(StepStatus.AWAITING)
                .sagaInstanceId(event.getSagaInstanceId())
                .stepName(eventDefinition.getNextStep().getStepName())
                .sagaStepDefinitionId(eventDefinition.getNextStep().getId())
                .build();

        SagaStepInstanceTransitionEvent transitionEvent = SagaStepInstanceTransitionEvent.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .sagaInstanceId(event.getSagaInstanceId())
                .sagaName(event.getSagaName())
                .creationTime(ZonedDateTime.now().toInstant().toEpochMilli())
                .build();
        transitionEvent.setNextStep(nextStep);
        transitionEvent.setPreviousStep(occurredStep);

        saveStepsInRepository(occurredStep, nextStep);
        eventInstanceRepository.save(transitionEvent);
    }

    public boolean isEventSuccessful(Event event) {
        return eventDefinitionRepository.findSagaStepDefinitionTransitionEventBySagaNameAndEventName(event.getSagaName(),
                event.getEventName()) != null;
    }

    public String getSagaNameBySagaId(Long sagaId) {
        return sagaStepInstanceRepository.findSagaStepInstancesBySagaInstanceId(sagaId).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can't find saga with id %d", sagaId)))
                .getSagaName();
    }

    private SagaStepInstance getOccurredSagaStepInstanceWithSuccessfulStatus(Event event, SagaStepDefinition occurredStepDefinition) {
        SagaStepInstance occurredStep = sagaStepInstanceRepository
                .findSagaStepInstanceBySagaInstanceIdAndSagaStepDefinitionId(event.getSagaInstanceId(), occurredStepDefinition.getId());

        if (occurredStep == null)
            occurredStep = SagaStepInstance.builder()
                    .stepStatus(StepStatus.AWAITING)
                    .sagaInstanceId(event.getSagaInstanceId())
                    .stepName(occurredStepDefinition.getStepName())
                    .sagaStepDefinitionId(occurredStepDefinition.getId())
                    .build();

        updateStepStatusOnSuccessful(occurredStep);
        return occurredStep;
    }

    private void updateStepStatusOnSuccessful(SagaStepInstance stepInstance) {
        stepInstance.setStepStatus(StepStatus.SUCCESSFUL);
    }

    private void saveStepsInRepository(SagaStepInstance occurredStep, SagaStepInstance nextStep) {
        sagaStepInstanceRepository.save(occurredStep);
        sagaStepInstanceRepository.save(nextStep);
    }

    private SagaStepDefinitionTransitionEvent getEventDefinition(Event event) {
        if (isEventSuccessful(event)) {
            return eventDefinitionRepository
                    .findSagaStepDefinitionTransitionEventBySagaNameAndEventName(event.getSagaName(), event.getEventName());
        }
        return eventDefinitionRepository.findSagaStepDefinitionTransitionEventBySagaNameAndFailedEventName(event.getSagaName(),
                event.getEventName());
    }
}
