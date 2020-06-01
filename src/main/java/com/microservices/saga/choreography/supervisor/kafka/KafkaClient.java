package com.microservices.saga.choreography.supervisor.kafka;

import com.microservices.saga.choreography.supervisor.domain.Event;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.exception.KafkaRuntimeException;
import com.microservices.saga.choreography.supervisor.service.EventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Service that responsible for interacting with kafka
 */
@Service
public class KafkaClient {
    /**
     * Kafka headers keys constants
     */
    private static final String EVENT_TYPE_KEY = "event-type";
    private static final String EVENT_ID_KEY = "event-id";
    private static final String SAGA_NAME_KEY = "saga-name";
    private static final String SAGA_ID_KEY = "saga-id";

    /**
     * is listening closed flag
     */
    private final AtomicBoolean isListeningClosed;

    /**
     * Message handler
     */
    private final EventHandler eventHandler;

    /**
     * Kafka consumer
     */
    private final KafkaConsumer<String, String> consumer;

    private final Set<String> listeningTopics;

    public KafkaClient(EventHandler eventHandler,
                       KafkaConsumer<String, String> kafkaConsumer) {
        this.listeningTopics = new HashSet<>();
        this.isListeningClosed = new AtomicBoolean(true);
        this.eventHandler = eventHandler;
        this.consumer = kafkaConsumer;
    }

    /**
     * Subscribe on topics to listen
     *
     * @param topicNames regex describing the naming rule for topics to subscribe to listen
     */
    public void subscribe(List<String> topicNames) {
        List<String> newTopics = topicNames.stream()
                .filter(topic -> !listeningTopics.contains(topic))
                .collect(Collectors.toList());
        if (!newTopics.isEmpty()) {
            listeningTopics.addAll(newTopics);
            consumer.subscribe(listeningTopics);
            if (isListeningClosed.get()) {
                startListeningTopics();
            }
        }
    }

    public void subscribeOnStepDefinition(SagaStepDefinition stepDefinition) {
        String successTopic = stepDefinition.getSuccessExecutionInfo().getKafkaSuccessExecutionInfo().getTopicPattern();
        String failTopic = stepDefinition.getFailExecutionInfo().getKafkaFailExecutionInfo().getTopicPattern();
        subscribe(Arrays.asList(successTopic, failTopic));
    }

    private void startListeningTopics() {
        if (!isListeningClosed.get()) {
            stopListeningTopics();
        }
        isListeningClosed.set(false);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                //noinspection InfiniteLoopStatement
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                    for (ConsumerRecord<String, String> record : records) {
                        eventHandler.handle(getEventFromHeaders(record.headers()));
                    }
                }
            } catch (WakeupException wakeupException) {
                if (!isListeningClosed.get()) {
                    throw new KafkaRuntimeException("Caught WakeupException, but isListeningClosed variable is false", wakeupException);
                }
            } finally {
                consumer.close();
            }
        });
        consumer.unsubscribe();
    }

    /**
     * Stop listening topics
     */
    public void stopListeningTopics() {
        isListeningClosed.set(true);
        consumer.wakeup();
    }

    private Event getEventFromHeaders(Headers headers) {
        Event.EventBuilder eventBuilder = Event.builder();
        for (Header header : headers) {
            switch (header.key()) {
                case EVENT_TYPE_KEY:
                    eventBuilder.eventName(new String(header.value()));
                    break;
                case EVENT_ID_KEY:
                    eventBuilder.eventId(ByteBuffer.wrap(header.value()).getLong());
                    break;
                case SAGA_NAME_KEY:
                    eventBuilder.sagaName(new String(header.value()));
                    break;
                case SAGA_ID_KEY:
                    eventBuilder.sagaInstanceId(ByteBuffer.wrap(header.value()).getLong());
                    break;
            }
        }
        return eventBuilder.build(); //TODO handle null fields
    }
}
