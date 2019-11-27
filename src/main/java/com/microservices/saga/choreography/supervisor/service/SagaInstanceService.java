package com.microservices.saga.choreography.supervisor.service;

import com.microservices.saga.choreography.supervisor.domain.*;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import com.microservices.saga.choreography.supervisor.dto.EventDto;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionTransitionEventRepository;
import com.microservices.saga.choreography.supervisor.repository.SagaStepInstanceRepository;
import com.microservices.saga.choreography.supervisor.repository.SagaStepInstanceTransitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SagaInstanceService {
    private SagaStepDefinitionTransitionEventRepository sagaStepDefinitionTransitionEventRepository;
    private SagaStepInstanceRepository sagaStepInstanceRepository;
    private SagaStepInstanceTransitionRepository sagaStepInstanceTransitionRepository;

    private SagaStepInstanceFactory sagaStepInstanceFactory;
    private SagaStepInstanceTransitionEventFactory sagaStepInstanceTransitionEventFactory;

    public void handleEventDto(EventDto eventDto) {
        SagaStepDefinitionTransitionEvent definitionTransitionEvent = sagaStepDefinitionTransitionEventRepository
                .findSagaStepDefinitionTransitionEventBySagaNameAndEventName(eventDto.getSagaName(), eventDto.getEventName());
        SagaStepDefinition occurredStepDefinition = definitionTransitionEvent.getPreviousStep();
        SagaStepInstance occurredStep = getOccurredSagaStepInstanceWithSuccessfulStatus(eventDto, occurredStepDefinition);
        SagaStepInstance nextStep = sagaStepInstanceFactory
                .createStepOfSagaInstanceFromEventAndSagaStepDefinition(eventDto, definitionTransitionEvent.getNextStep());

        SagaStepInstanceTransitionEvent transitionEvent = sagaStepInstanceTransitionEventFactory
                .createSagaStepInstanceTransitionEventFromEventDto(eventDto);
        transitionEvent.setNextStep(nextStep);
        transitionEvent.setPreviousStep(occurredStep);

        saveStepsInRepository(occurredStep, nextStep);
        sagaStepInstanceTransitionRepository.save(transitionEvent);
    }

    private SagaStepInstance getOccurredSagaStepInstanceWithSuccessfulStatus(EventDto eventDto, SagaStepDefinition occurredStepDefinition) {
        SagaStepInstance occurredStep = sagaStepInstanceRepository
                .findSagaStepInstanceBySagaInstanceIdAndSagaStepDefinitionId(eventDto.getSagaInstanceId(), occurredStepDefinition.getId());

        if (occurredStep == null)
            occurredStep = sagaStepInstanceFactory
                    .createStepOfSagaInstanceFromEventAndSagaStepDefinition(eventDto, occurredStepDefinition);

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


}
