package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinitionTransitionEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * Manages relationships between the nodes of a template graph
 */
public interface SagaStepDefinitionTransitionEventRepository extends Neo4jRepository<SagaStepDefinitionTransitionEvent, Long> {
    /**
     * Returns the event along with the nodes of the template graph connected by this event
     * by event name for given saga
     *
     * @param sagaName  - the name of the saga
     * @param eventName - the name of the event
     * @return relationship between two nodes
     */
    SagaStepDefinitionTransitionEvent findSagaStepDefinitionTransitionEventBySagaNameAndEventName(String sagaName, String eventName);

    /**
     * Retrieves all the event for given saga
     *
     * @param sagaName - the name of the saga
     * @return all relationships of the specific saga
     */
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaName(String sagaName);

    /**
     * Returns outgoing events for specified saga step
     *
     * @param sagaName     - the name of the saga
     * @param previousStep - node of the template graph
     * @return list of template graph relationships with specified previousStep
     */
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaNameAndPreviousStep(String sagaName, SagaStepDefinition previousStep);

    /**
     * Returns incoming events for specified saga step
     *
     * @param sagaName - the name of the saga
     * @param nextStep - node of the template graph
     * @return list of template graph relationships with specified nextStep
     */
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaNameAndNextStep(String sagaName, SagaStepDefinition nextStep);
}
