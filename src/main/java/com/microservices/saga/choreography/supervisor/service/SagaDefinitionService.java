package com.microservices.saga.choreography.supervisor.service;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEventFactory;
import com.microservices.saga.choreography.supervisor.dto.definition.SagaStepDefinitionDto;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionRepository;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionTransitionEventRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SagaDefinitionService {
    private final SagaStepDefinitionRepository stepDefinitionRepository;
    private final SagaStepDefinitionTransitionEventRepository transitionEventRepository;
    private final SagaStepDefinitionTransitionEventFactory eventFactory;
    private final ModelMapper mapper;

    public SagaStepDefinition addDefinition(SagaStepDefinitionDto stepDefinitionDto) {
        SagaStepDefinition stepDefinition = mapper.map(stepDefinitionDto, SagaStepDefinition.class);
        @NonNull List<String> previousSteps = stepDefinitionDto.getPreviousSteps();
        saveTransitionEvent(stepDefinition, previousSteps);
        return stepDefinitionRepository.save(stepDefinition);
    }

    public SagaStepDefinition updateDefinition(Long id, SagaStepDefinitionDto stepDefinitionDto) throws Exception {
        SagaStepDefinition newDefinition = mapper.map(stepDefinitionDto, SagaStepDefinition.class);
        Optional<SagaStepDefinition> foundOptionalDefinition = stepDefinitionRepository.findById(id);
        if (foundOptionalDefinition.isEmpty()) throw new Exception("Definition with id " + id + " is not present");
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

    private void deleteTransitions(@NonNull List<SagaStepDefinitionTransitionEvent> transitionEvents) {
        transitionEvents.forEach(transitionEventRepository::delete);
    }

    private void saveTransitionEvent(SagaStepDefinition stepDefinition, List<String> previousSteps) {
        for (String previousStep : previousSteps) {
            SagaStepDefinition previousStepDefinition = stepDefinitionRepository
                    .findSagaStepDefinitionBySagaNameAndStepName(stepDefinition.getSagaName(), previousStep);
            SagaStepDefinitionTransitionEvent definitionTransitionEvent = eventFactory
                    .createSageStepDefinitionTransitionEvent(stepDefinition, previousStepDefinition);
            transitionEventRepository.save(definitionTransitionEvent);
        }
    }
}
