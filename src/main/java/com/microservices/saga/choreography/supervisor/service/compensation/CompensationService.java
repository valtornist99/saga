package com.microservices.saga.choreography.supervisor.service.compensation;

import com.microservices.saga.choreography.supervisor.service.GraphService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
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
     * Kafka producer
     */
    private final KafkaProducer<String, String> producer;

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
                               KafkaProducer<String, String> producer) {
        sagaCompensations = new HashSet<>();
        this.producer = producer;
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
                .forEach(message -> sendMessage(message.getTopic(), message.getHeaders(), message.getEventMessage()));
    }

    /**
     * Sending messages
     */
    public void sendMessage(String topicName, Headers headers, String message) {
        producer.send(new ProducerRecord<>(topicName, message));
    }
}
