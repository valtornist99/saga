package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.SagaStepInstance;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * The repository that stores the nodes of the saga step instance graph
 */
public interface SagaStepInstanceRepository extends Neo4jRepository<SagaStepInstance, Long> {
    /**
     * @param sagaInstanceId - the id of the saga instance
     * @param stepName       - the name of the step
     * @return node of the saga instance graph
     */
    SagaStepInstance findSagaStepInstanceBySagaInstanceIdAndStepName(Long sagaInstanceId, String stepName);

    /**
     * @param sagaInstanceId       - the id of the saga instance
     * @param sagaStepDefinitionId - the id of the saga step definition
     * @return node of the saga instance graph
     */
    SagaStepInstance findSagaStepInstanceBySagaInstanceIdAndSagaStepDefinitionId(Long sagaInstanceId, Long sagaStepDefinitionId);

    /**
     * @param sagaInstanceId - the id of the saga instance
     * @param sagaName       - the name of the saga
     * @return all nodes of the saga step instance graph
     */
    List<SagaStepInstance> findSagaStepInstancesBySagaInstanceIdAndSagaName(Long sagaInstanceId, String sagaName);
}
