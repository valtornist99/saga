package com.microservices.choreographyorchestrator.repository;

import com.microservices.choreographyorchestrator.domain.MetaNode;
import com.microservices.choreographyorchestrator.domain.Relation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface RelationRepository extends Neo4jRepository<Relation, Long> {
    Collection<Relation> findMetaNodeRelationByStartNode(MetaNode startNode);

    Collection<Relation> findMetaNodeRelationByEndNode(MetaNode endNode);

    Collection<Relation> findMetaNodeRelationByStartNodeAndEndNode(MetaNode startNode, MetaNode endNode);
}
