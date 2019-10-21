package com.microservices.choreographyorchestrator.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Getter
@Setter
public class Node {
    private Long sagaInstanceId;
    private String sagaName;
    private Long metaNodeId;
}
