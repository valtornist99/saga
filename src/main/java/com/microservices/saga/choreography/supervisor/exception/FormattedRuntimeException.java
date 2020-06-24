package com.microservices.saga.choreography.supervisor.exception;

import com.microservices.saga.choreography.supervisor.components.SagaMetrics;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;

public class FormattedRuntimeException extends RuntimeException {
    @Autowired
    private SagaMetrics sagaMetrics;

    public FormattedRuntimeException(String messagePattern, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage());
        sagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }

    public FormattedRuntimeException(String messagePattern, Throwable cause, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage(), cause);
        sagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }
}
