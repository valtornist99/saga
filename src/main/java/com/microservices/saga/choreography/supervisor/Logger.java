package com.microservices.saga.choreography.supervisor;

import com.microservices.saga.choreography.supervisor.components.SagaMetrics;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Logger {
    private org.slf4j.Logger log;

    @Autowired
    private SagaMetrics metrics;

    public Logger(Class clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

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
