package com.microservices.saga.choreography.supervisor.exception;

import com.microservices.saga.choreography.supervisor.SagaMetrics;
import org.slf4j.helpers.MessageFormatter;

public class FormattedRuntimeException extends RuntimeException {
    public FormattedRuntimeException(String messagePattern, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage());
        SagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }

    public FormattedRuntimeException(String messagePattern, Throwable cause, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage(), cause);
        SagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }
}
