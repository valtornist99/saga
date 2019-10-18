package com.microservices.choreographyorchestrator.repository;


import com.microservices.choreographyorchestrator.domain.MetaNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface MetaNodeRepository extends Neo4jRepository<MetaNode, Long> {
    MetaNode findMetaGraphNodeBySagaNameAndStepName(String sagaName, String stepName);

    Collection<MetaNode> findMetaNodesByStepName(String stepName);

    Collection<MetaNode> findMetaNodesBySagaName(String sagaName);

    Collection<MetaNode> findMetaGraphNodesBySagaName(String sagaName);
}
