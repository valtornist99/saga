package com.microservices.choreographyorchestrator.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Getter
@Setter
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Node {
    private Long sagaInstanceId;
    private String sagaName;
    private Long metaNodeId;
}
