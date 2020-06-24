package com.microservices.saga.choreography.supervisor.exception;

import com.microservices.saga.choreography.supervisor.components.SagaMetrics;
import org.springframework.beans.factory.annotation.Autowired;

public class KafkaRuntimeException extends FormattedRuntimeException {
    @Autowired
    private SagaMetrics sagaMetrics;

    public KafkaRuntimeException(String messagePattern, Object... params) {
        super(messagePattern, params);
    }

    public KafkaRuntimeException(String messagePattern, Throwable cause, Object... params) {
        super(messagePattern, cause, params);
        sagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }
}
