package com.example.kafkalistener.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//havingValue = false (будет создана если false)
@ConditionalOnProperty(prefix = "enable", name = "kafkaListener", matchIfMissing = true)
public class ConsumerKafka {
    @KafkaListener(topics = {"output"}, groupId = "default-group")
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        log.info("Key: {} - Message: {}",
                consumerRecord.key().replace("�", ""),
                consumerRecord.value().replace("�", ""));
    }
}
