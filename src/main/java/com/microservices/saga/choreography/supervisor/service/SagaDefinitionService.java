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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SagaDefinitionService {
    private SagaStepDefinitionRepository stepDefinitionRepository;
    private SagaStepDefinitionTransitionEventRepository transitionEventRepository;
    private SagaStepDefinitionTransitionEventFactory eventFactory;
    private ModelMapper mapper;

    public SagaStepDefinition addDefinition(SagaStepDefinitionDto stepDefinitionDto) {
        SagaStepDefinition stepDefinition = mapper.map(stepDefinitionDto, SagaStepDefinition.class);
        String previousStepName = stepDefinitionDto.getPreviousStep();
        saveTransitionEvent(stepDefinition, previousStepName);
        return stepDefinitionRepository.save(stepDefinition);
    }

    public SagaStepDefinition updateDefinition(Long id, SagaStepDefinitionDto stepDefinitionDto) throws Exception {
        SagaStepDefinition newDefinition = mapper.map(stepDefinitionDto, SagaStepDefinition.class);
        Optional<SagaStepDefinition> foundOptionalDefinition = stepDefinitionRepository.findById(id);
        if(foundOptionalDefinition.isEmpty()) throw new Exception("Definition with id " + id + " is not present");
        SagaStepDefinition foundDefinition = foundOptionalDefinition.get();
        foundDefinition.update(newDefinition);
        return stepDefinitionRepository.save(foundDefinition);
    }

    public void deleteDefinition(Long id) {
        stepDefinitionRepository.findById(id)
                .ifPresent(sagaStepDefinition -> {
                    List<SagaStepDefinitionTransitionEvent> allEventsWithDefinition = getAllEventsWithDefinition(sagaStepDefinition);
                    deleteTransitions(allEventsWithDefinition);
                });
    }

    private List<SagaStepDefinitionTransitionEvent> getAllEventsWithDefinition(SagaStepDefinition definition) {
        List<SagaStepDefinitionTransitionEvent> previousTransitions = transitionEventRepository
                .findSagaStepDefinitionTransitionEventsByNextStep(definition);
        List<SagaStepDefinitionTransitionEvent> nextTransitions = transitionEventRepository
                .findSagaStepDefinitionTransitionEventsByPreviousStep(definition);

        return Stream.concat(previousTransitions.stream(), nextTransitions.stream()).collect(Collectors.toList());
    }

    private void deleteTransitions(List<SagaStepDefinitionTransitionEvent> transitionEvents) {
        transitionEvents.forEach(transition -> transitionEventRepository.delete(transition));
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
