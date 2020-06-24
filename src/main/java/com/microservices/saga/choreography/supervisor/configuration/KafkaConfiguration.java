package com.microservices.saga.choreography.supervisor.configuration;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Kafka configuration class
 */
@Configuration
public class KafkaConfiguration {
    /**
     * Library serialization path
     */
    private static final String SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    /**
     * Library deserialization path
     */
    private static final String DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";

    /**
     * List of kafka servers
     */
    @Value("${spring.kafka.bootstrap-server}")
    private String brokers;

    /**
     * Group id
     */
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public KafkaProducer<String, String> getKafkaProducer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", brokers);
        properties.setProperty("key.serializer", SERIALIZER);
        properties.setProperty("value.serializer", SERIALIZER);

        return new KafkaProducer<>(properties);
    }

    @Bean
    public KafkaConsumer<String, String> getConsumer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", brokers);
        properties.setProperty("group.id", groupId);
        properties.setProperty("key.deserializer", DESERIALIZER);
        properties.setProperty("value.deserializer", DESERIALIZER);
        properties.setProperty("auto.offset.reset", "earliest");

        return new KafkaConsumer<>(properties);
    }
}
