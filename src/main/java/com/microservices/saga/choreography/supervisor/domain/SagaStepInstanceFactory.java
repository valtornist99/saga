package com.microservices.saga.choreography.supervisor.domain;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.dto.EventDto;
import org.springframework.stereotype.Component;

@Component
public class SagaStepInstanceFactory {
    public SagaStepInstance createStepOfSagaInstanceFromEventAndSagaStepDefinition(EventDto eventDto, SagaStepDefinition definitionOfTheStep) {
        SagaStepInstance sagaStepInstance = new SagaStepInstance();
        sagaStepInstance.setStepStatus(StepStatus.AWAITING);
        sagaStepInstance.setSagaInstanceId(eventDto.getSagaInstanceId());
        sagaStepInstance.setStepName(definitionOfTheStep.getStepName());
        sagaStepInstance.setSagaStepDefinitionId(definitionOfTheStep.getId());
        return sagaStepInstance;
    }
}
