package com.microservices.choreographyorchestrator.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Node {
    private Long sagaInstanceId;
    private String sagaName;
    private Long metaNodeId;
}
