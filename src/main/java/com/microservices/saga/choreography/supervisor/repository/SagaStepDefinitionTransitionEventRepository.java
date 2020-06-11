package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.entity.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.domain.entity.SagaStepDefinitionTransitionEvent;
import org.springframework.data.neo4j.annotation.Depth;
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
     * @param sagaName  the name of the saga
     * @param eventName the name of the event
     * @return relationship between two nodes
     */
    @Depth(4)
    SagaStepDefinitionTransitionEvent findSagaStepDefinitionTransitionEventBySagaNameAndEventName(String sagaName, String eventName);

    /**
     * Returns the event along with the nodes of the template graph connected by this event
     * by failed event name for given saga
     *
     * @param sagaName        the name of the saga
     * @param failedEventName the name of the event
     * @return relationship between two nodes
     */
    @Depth(4)
    SagaStepDefinitionTransitionEvent findSagaStepDefinitionTransitionEventBySagaNameAndFailedEventName(String sagaName,
                                                                                                        String failedEventName);

    /**
     * Retrieves all the event for given saga
     *
     * @param sagaName the name of the saga
     * @return all relationships of the specific saga
     */
    @Depth(4)
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsBySagaName(String sagaName);

    /**
     * Returns outgoing events for specified saga step
     *
     * @param previousStep node of the template graph
     * @return list of template graph relationships with specified previousStep
     */
    @Depth(4)
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsByPreviousStep(SagaStepDefinition previousStep);

    /**
     * Returns incoming events for specified saga step
     *
     * @param nextStep node of the template graph
     * @return list of template graph relationships with specified nextStep
     */
    @Depth(4)
    List<SagaStepDefinitionTransitionEvent> findSagaStepDefinitionTransitionEventsByNextStep(SagaStepDefinition nextStep);
}
