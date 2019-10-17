package com.microservices.choreographyorchestrator.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.microservices.choreographyorchestrator.domain.meta.CompensationInfo;
import com.microservices.choreographyorchestrator.domain.meta.ExecutionInfo;
import com.microservices.choreographyorchestrator.domain.meta.RetryInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class MetaNode {
    @Id
    @GeneratedValue
    private Long id;
    @Getter
    @Setter
    private String sagaName;
    @Getter
    @Setter
    private String stepName;
    @Getter
    @Setter
    private ExecutionInfo successExecutionInfo;
    @Getter
    @Setter
    private ExecutionInfo failExecutionInfo;
    @Getter
    @Setter
    private CompensationInfo compensationInfo;
    @Getter
    @Setter
    private RetryInfo retryInfo;
}


