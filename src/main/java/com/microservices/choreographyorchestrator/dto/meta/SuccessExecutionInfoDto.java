package com.microservices.choreographyorchestrator.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SuccessExecutionInfoDto {
    @JsonProperty(value = "channel_name")
    private String channelName;
    @JsonProperty(value = "event_type")
    private String eventType;
}
