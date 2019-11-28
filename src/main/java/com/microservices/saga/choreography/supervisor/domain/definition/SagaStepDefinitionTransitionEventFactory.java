package com.microservices.saga.choreography.supervisor.domain.definition;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class SagaStepDefinitionTransitionEventFactory {
    public SagaStepDefinitionTransitionEvent createSageStepDefinitionTransitionEvent(SagaStepDefinition previousStep, SagaStepDefinition nextStep) {
        String sagaName = nextStep.getSagaName();
        SagaStepDefinitionTransitionEvent definitionTransitionEvent = new SagaStepDefinitionTransitionEvent();
        definitionTransitionEvent.setSagaName(sagaName);
        String eventType = nextStep.getSuccessExecutionInfo().getKafkaSuccessExecutionInfo().getEventType();
        definitionTransitionEvent.setEventName(eventType);
        definitionTransitionEvent.setCreationTime(ZonedDateTime.now().toInstant().toEpochMilli());
        definitionTransitionEvent.setPreviousStep(previousStep);
        definitionTransitionEvent.setNextStep(nextStep);
        return definitionTransitionEvent;
    }
}
