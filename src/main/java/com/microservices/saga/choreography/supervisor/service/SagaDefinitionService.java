package com.microservices.saga.choreography.supervisor.service;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEventFactory;
import com.microservices.saga.choreography.supervisor.dto.definition.SagaStepDefinitionDto;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionRepository;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionTransitionEventRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SagaDefinitionService {
    private SagaStepDefinitionRepository stepDefinitionRepository;
    private SagaStepDefinitionTransitionEventRepository transitionEventRepository;
    private SagaStepDefinitionTransitionEventFactory eventFactory;
    private ModelMapper mapper;

    public void handleSagaStepDefinitionDto(SagaStepDefinitionDto stepDefinitionDto) {
        SagaStepDefinition stepDefinition = mapper.map(stepDefinitionDto, SagaStepDefinition.class);
        String previousStepName = stepDefinitionDto.getPreviousStep();
        stepDefinitionRepository.save(stepDefinition);
        saveTransitionEvent(stepDefinition, previousStepName);
    }

    private void saveTransitionEvent(SagaStepDefinition stepDefinition, String previousStepName) {
        if (previousStepName == null) return;
        SagaStepDefinition previousStep = stepDefinitionRepository
                .findSagaStepDefinitionBySagaNameAndStepName(stepDefinition.getSagaName(), previousStepName);
        SagaStepDefinitionTransitionEvent definitionTransitionEvent = eventFactory
                .createSageStepDefinitionTransitionEvent(stepDefinition, previousStep);
        transitionEventRepository.save(definitionTransitionEvent);
    }
}
