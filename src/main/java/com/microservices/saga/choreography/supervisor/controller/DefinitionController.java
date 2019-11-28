package com.microservices.saga.choreography.supervisor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.dto.definition.SagaStepDefinitionDto;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionRepository;
import com.microservices.saga.choreography.supervisor.service.SagaDefinitionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/definition")
public class DefinitionController {
    private SagaDefinitionService definitionService;
    private SagaStepDefinitionRepository stepDefinitionRepository;
    private ObjectMapper mapper;

    @PostMapping(value = "/add", headers = {"Content-type=application/json"})
    public void addDefinition(@RequestBody SagaStepDefinitionDto stepDefinitionDto) {
        if (stepDefinitionDto == null) return;
        definitionService.handleSagaStepDefinitionDto(stepDefinitionDto);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public String getStepDefinition(@PathVariable long id) {
        Optional<SagaStepDefinition> stepDefinitionRepositoryById = stepDefinitionRepository.findById(id);
        if (stepDefinitionRepositoryById.isPresent()) {
            SagaStepDefinition stepDefinition = stepDefinitionRepositoryById.get();
            try {
                return mapper.writeValueAsString(stepDefinition);
            } catch (JsonProcessingException ignored) {
            }
        }
        return "";
    }
}
