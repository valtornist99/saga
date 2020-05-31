package com.microservices.saga.choreography.supervisor.service;

import com.microservices.saga.choreography.supervisor.domain.Event;
import com.microservices.saga.choreography.supervisor.domain.Message;
import com.microservices.saga.choreography.supervisor.domain.definition.KafkaCompensation;
import com.microservices.saga.choreography.supervisor.domain.definition.SagaStepDefinition;
import com.microservices.saga.choreography.supervisor.dto.definition.SagaStepDefinitionDto;
import com.microservices.saga.choreography.supervisor.exception.StepDefinitionNotFoundException;
import com.microservices.saga.choreography.supervisor.kafka.KafkaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GraphService {
    /**
     * Kafka headers keys constants
     */
    private static final String EVENT_TYPE_KEY = "event-type";
    private static final String SAGA_ID_KEY = "saga-id";

    private final DefinitionService definitionService;
    private final InstanceService instanceService;
    private final KafkaClient kafkaClient;

    public SagaStepDefinition addDefinition(SagaStepDefinitionDto definitionDto) {
        SagaStepDefinition stepDefinition = definitionService.addDefinition(definitionDto);
        String successPattern = stepDefinition.getSuccessExecutionInfo().getKafkaSuccessExecutionInfo().getTopicPattern();
        String failPattern = stepDefinition.getFailExecutionInfo().getKafkaFailExecutionInfo().getTopicPattern();
        kafkaClient.subscribe(Pattern.compile(successPattern));
        kafkaClient.subscribe(Pattern.compile(failPattern));
        return stepDefinition;
    }

    public SagaStepDefinition updateDefinition(Long definitionId, SagaStepDefinitionDto definitionDto) {
        try {
            return definitionService.updateDefinition(definitionId, definitionDto);
        } catch (StepDefinitionNotFoundException e) {
            log.error("Can't found definition {} for update", definitionId);
        }
        return new SagaStepDefinition();
    }

    public void deleteDefinition(Long definitionId) {
        definitionService.deleteDefinition(definitionId);
    }

    public void handleSagaInstanceEvent(Event event) {
        instanceService.handleEvent(event);
    }

    public boolean isEventSuccessful(Event event) {
        return instanceService.isEventSuccessful(event);
    }

    public Queue<Message> getMessagesQueueToCompensation(Long sagaId) {
        String sagaName = instanceService.getSagaNameBySagaId(sagaId);
        List<SagaStepDefinition> endNodes = definitionService.getEndNodesOfSaga(sagaName);

        return searchByBreadth(endNodes).stream()
                .map(stepDefinition -> getMessageToNode(sagaId, stepDefinition))
                .collect(Collectors.toCollection(PriorityQueue::new));
    }

    private Message getMessageToNode(Long sagaId, SagaStepDefinition stepDefinition) {
        Headers headers = getHeadersToNode(sagaId, stepDefinition);
        KafkaCompensation kafkaCompensation = stepDefinition.getCompensationInfo().getKafkaCompensation();
        return new Message(headers, kafkaCompensation.getTopicName(), "COMPENSATION");
    }

    private Headers getHeadersToNode(Long sagaId, SagaStepDefinition stepDefinition) {
        Headers headers = new RecordHeaders();
        KafkaCompensation kafkaCompensation = stepDefinition.getCompensationInfo().getKafkaCompensation();
        headers.add(EVENT_TYPE_KEY, kafkaCompensation.getEventType().getBytes());
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(sagaId);
        headers.add(SAGA_ID_KEY, buffer.array());
        return headers;
    }

    private Queue<SagaStepDefinition> searchByBreadth(List<SagaStepDefinition> endNodes) {
        Queue<SagaStepDefinition> visitedNodes = new PriorityQueue<>();
        Queue<SagaStepDefinition> unvisitedNodes = new PriorityQueue<>(endNodes);

        while(!unvisitedNodes.isEmpty()) {
            Queue<SagaStepDefinition> newNodes = unvisitedNodes
                    .stream()
                    .flatMap(node -> definitionService.getIncomingSteps(node).stream())
                    .filter(node -> !visitedNodes.contains(node))
                    .collect(Collectors.toCollection(PriorityQueue::new));
            visitedNodes.addAll(unvisitedNodes);
            unvisitedNodes = newNodes;
        }
        return visitedNodes;
    }
}
