package com.microservices.saga.choreography.supervisor.controller;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.dto.definition.SagaStepDefinitionDto;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionRepository;
import com.microservices.saga.choreography.supervisor.service.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/definition")
public class DefinitionController {
    private final GraphService graphService;
    private final SagaStepDefinitionRepository stepDefinitionRepository;

    @PostMapping(value = "", headers = {"Content-type=application/json"})
    public ResponseEntity<SagaStepDefinition> addDefinition(@RequestBody @Valid SagaStepDefinitionDto stepDefinitionDto) {
        return ResponseEntity.ok().body(graphService.addDefinition(stepDefinitionDto));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SagaStepDefinition> getStepDefinition(@PathVariable Long id) {
        Optional<SagaStepDefinition> stepDefinitionRepositoryById = stepDefinitionRepository.findById(id);
        stepDefinitionRepositoryById.ifPresent(stepDefinition -> ResponseEntity.ok().body(stepDefinition));
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "/{definitionId}", produces = "application/json")
    public ResponseEntity<SagaStepDefinition> updateStepDefinition(@PathVariable Long definitionId,
                                                                   @RequestBody @Valid SagaStepDefinitionDto stepDefinitionDto) {
        return ResponseEntity.ok().body(graphService.updateDefinition(definitionId, stepDefinitionDto));
    }

    @DeleteMapping(value = "/{definitionId}")
    public void deleteDefinition(@PathVariable Long definitionId) {
        graphService.deleteDefinition(definitionId);
    }
}
