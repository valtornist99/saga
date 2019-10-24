package com.microservices.saga.choreography.supervisor.repository;

import com.microservices.saga.choreography.supervisor.domain.SagaStepInstance;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface SagaStepInstanceRepository extends Neo4jRepository<SagaStepInstance, Long> {
    SagaStepInstance findSagaStepInstanceBySagaInstanceId(Long sagaInstanceId);

    List<SagaStepInstance> findSagaStepInstancesBySagaName(String sagaName);
}
