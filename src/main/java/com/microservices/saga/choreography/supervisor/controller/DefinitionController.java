package com.microservices.saga.choreography.supervisor.controller;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.dto.definition.SagaStepDefinitionDto;
import com.microservices.saga.choreography.supervisor.repository.SagaStepDefinitionRepository;
import com.microservices.saga.choreography.supervisor.service.SagaDefinitionService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/definition")
public class DefinitionController {
    private final SagaDefinitionService definitionService;
    private final SagaStepDefinitionRepository stepDefinitionRepository;

    @PostMapping(value = "/add", headers = {"Content-type=application/json"})
    public ResponseEntity<SagaStepDefinition> addDefinition(@RequestBody @Valid SagaStepDefinitionDto stepDefinitionDto) {
        return ResponseEntity.ok().body(definitionService.addDefinition(stepDefinitionDto));
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<SagaStepDefinition> getStepDefinition(@PathVariable Long id) {
        Optional<SagaStepDefinition> stepDefinitionRepositoryById = stepDefinitionRepository.findById(id);
        stepDefinitionRepositoryById.ifPresent(stepDefinition -> ResponseEntity.ok().body(stepDefinition));
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<SagaStepDefinition> updateStepDefinition(@PathVariable Long id,
                                                                   @RequestBody @Valid SagaStepDefinitionDto stepDefinitionDto) throws Exception {
        return ResponseEntity.ok().body(definitionService.updateDefinition(id, stepDefinitionDto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteDefinition(@PathVariable Long id) {
        definitionService.deleteDefinition(id);
    }
}
