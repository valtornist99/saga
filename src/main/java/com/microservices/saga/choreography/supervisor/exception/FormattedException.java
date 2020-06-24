package com.microservices.saga.choreography.supervisor.exception;

import com.microservices.saga.choreography.supervisor.components.SagaMetrics;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Formatted exception class
 */
public class FormattedException extends Exception {
    @Autowired
    private SagaMetrics sagaMetrics;

    public FormattedException(String messagePattern, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage());
        sagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }

    public FormattedException(String messagePattern, Throwable cause, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage(), cause);
        sagaMetrics.incrementCoordinatorExceptionsThrown(this);
    }
}
