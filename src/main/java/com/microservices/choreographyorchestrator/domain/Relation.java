package com.microservices.choreographyorchestrator.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@Getter
@Setter
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "EVENT")
public class Relation {
    @Id
    @GeneratedValue
    private Long eventId;
    @StartNode
    private MetaNode startNode;
    @EndNode
    private MetaNode endNode;
    @DateLong
    private Date creationDate;
    private String eventName;
}
