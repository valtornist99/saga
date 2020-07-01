package com.microservices.saga.choreography.supervisor;

import com.microservices.saga.choreography.supervisor.components.SagaMetrics;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;

@AllArgsConstructor
public class KPIEventsLogger {
    private Logger log;
    private SagaMetrics metrics;

    public void trace(String message) {
        log.trace(message);
    }

    public void debug(String message) {
        log.debug(message);
    }

    public void info(String message) {
        log.info(message);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void error(String message) {
        log.error(message);
    }
}
