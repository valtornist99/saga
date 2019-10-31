package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * The repository that stores relationships between the nodes of a template class
 */
public interface SagaStepDefinitionTransitionEventRepository extends Neo4jRepository<SagaStepDefinitionTransitionEvent, Long> {
    /**
     * Function that returns the relationship between the nodes of a template graph by the name of the saga and the name of the event
     *
     * @param sagaName  - the name of the saga
     * @param eventName - the name of the event
     * @return relationship between two nodes
     */
    SagaStepDefinitionTransitionEvent findSagaStepDefinitionTransitionEventBySagaNameAndEventName(String sagaName, String eventName);

    /**
     * Function to get all the relationships of the nodes in the template graph of a specific saga
     *
     * @param sagaName - the name of the saga
     * @return all relationships of the specific saga
     */
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaName(String sagaName);

    /**
     * Function that returns a list of template graph relationships with the specified previous step
     *
     * @param sagaName     - the name of the saga
     * @param previousStep - node of the template graph
     * @return list of template graph relationships with specified previousStep
     */
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaNameAndPreviousStep(String sagaName, SagaStepDefinition previousStep);

    /**
     * Function that returns a list of template graph relationships with the specified next step
     *
     * @param sagaName - the name of the saga
     * @param nextStep - node of the template graph
     * @return list of template graph relationships with specified nextStep
     */
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaNameAndNextStep(String sagaName, SagaStepDefinition nextStep);
}
