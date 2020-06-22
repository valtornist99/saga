package com.microservices.saga.choreography.supervisor.exception;

import com.microservices.saga.choreography.supervisor.SagaMetrics;

public class KafkaRuntimeException extends FormattedRuntimeException {
    public KafkaRuntimeException(String messagePattern, Object... params) {
        super(messagePattern, params);
    }

    public KafkaRuntimeException(String messagePattern, Throwable cause, Object... params) {
        super(messagePattern, cause, params);
        SagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }
}
