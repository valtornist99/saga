package com.microservices.choreographyorchestrator.repository;

import com.microservices.choreographyorchestrator.domain.Node;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface NodeRepository extends Neo4jRepository<Node, Long> {
    Node findNodeBySagaInstanceId(Long sagaInstanceId);
    Collection<Node> findNodesBySagaName(String sagaName);
}
