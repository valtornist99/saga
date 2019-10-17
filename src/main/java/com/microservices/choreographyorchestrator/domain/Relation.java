package com.microservices.choreographyorchestrator.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@JsonIdentityInfo(generator= JSOGGenerator.class)
@RelationshipEntity(type = "EVENT")
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    @Id
    @GeneratedValue
    @Getter
    private Long eventId;
    @StartNode
    @Getter
    private MetaNode startNode;
    @EndNode
    @Getter
    private MetaNode endNode;
    @DateLong
    @Getter
    private Date creationDate;
    @Getter
    private String eventName;
}
