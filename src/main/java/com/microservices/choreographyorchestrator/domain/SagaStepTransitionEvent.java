package com.microservices.choreographyorchestrator.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@Getter
@Setter
@RelationshipEntity(type = "EVENT")
public class SagaStepTransitionEvent {
    @Id
    @GeneratedValue
    private Long eventId;
    private String eventName;
    @StartNode
    private SagaStepDefinition previousStep;
    @EndNode
    private SagaStepDefinition nextStep;
    @DateLong
    private Date creationDate;
}
