package com.microservices.choreographyorchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventDto {
    private Long eventId;

    private Long sagaInstanceId;

    private String eventName;

    private String sagaName;
}
