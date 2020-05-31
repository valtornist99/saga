package com.microservices.saga.choreography.supervisor.service.compensation;

import com.microservices.saga.choreography.supervisor.kafka.KafkaClient;
import com.microservices.saga.choreography.supervisor.service.GraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Service that responsible for compensation
 */
@Service
@Slf4j
public class CompensationService {
    /**
     * Message sender
     */
    private final KafkaClient kafkaClient;

    /**
     * Graph handler
     */
    private final GraphService graphService;

    /**
     * Compensation map
     */
    private final Set<Long> sagaCompensations;

    /**
     * Constructor
     */
    public CompensationService(GraphService graphService,
                               KafkaClient kafkaClient) {
        sagaCompensations = new HashSet<>();
        this.kafkaClient = kafkaClient;
        this.graphService = graphService;
    }

    /**
     * Starts compensation
     */
    public synchronized void compensate(Long sagaId) {
        log.info("Request for compensation. Saga {}", sagaId);
        if (!sagaCompensations.contains(sagaId)) {
            log.info("Start compensation. Saga {}", sagaId);
            sagaCompensations.add(sagaId);
            startCompensation(sagaId);
        } else {
            log.info("Saga already compensated. Saga {}", sagaId);
        }
    }

    /**
     * Sending compensation messages
     *
     * @param sagaId id of saga to start compensation
     */
    private void startCompensation(Long sagaId) {
        graphService.getMessagesQueueToCompensation(sagaId)
                .forEach(message -> kafkaClient.sendMessage(message.getTopic(), message.getHeaders(), message.getEventMessage()));
    }
}
