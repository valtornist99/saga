package com.microservices.choreographyorchestrator.dto;
import lombok.Data;

@Data
public class EventDto {
    private Long eventId;
    private Long sagaInstanceId;
    private String eventName;
    private String sagaName;

}
