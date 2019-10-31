package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.SagaStepInstance;
import com.microservices.saga.choreography.supervisor.domain.SagaStepInstanceTransitionEvent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * The repository that stores the relationships between nodes of the saga step instance graph
 */
public interface SagaStepInstanceTransitionRepository extends Neo4jRepository<SagaStepInstanceTransitionEvent, Long> {

    /**
     * Function that returns the relationship between two nodes of the saga instance graph
     *
     * @param sagaInstanceId - the id of the saga instance
     * @param eventName      - the name of the event
     * @return relationship between two node of the saga instance graph
     */
    SagaStepInstanceTransitionEvent findSagaStepInstanceTransitionEventBySagaInstanceIdAndEventName(Long sagaInstanceId, String eventName);

    /**
     * Function that returns list of the relationships between nodes of the saga instance graph
     *
     * @param previousStep - node of the saga instance graph
     * @return list of the relationships where specified previous step
     */
    List<SagaStepInstanceTransitionEvent> findSagaStepInstanceTransitionEventsByPreviousStep(SagaStepInstance previousStep);

    /**
     * Function that returns list of the relationships between nodes of the saga instance graph
     *
     * @param nextStep - node of the saga instance graph
     * @return list of the relationships where specified next step
     */
    List<SagaStepInstanceTransitionEvent> findSagaStepInstanceTransitionEventsByNextStep(SagaStepInstance nextStep);
}
