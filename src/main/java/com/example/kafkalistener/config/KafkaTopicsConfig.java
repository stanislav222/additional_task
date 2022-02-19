package com.example.kafkalistener.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    @Bean
    public NewTopic topicFirst() {
        return TopicBuilder.name("input")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic topicSecond() {
        return TopicBuilder.name("output")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
