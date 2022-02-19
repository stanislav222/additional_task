package com.example.kafkalistener.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
@Slf4j
@Component
public class ProducerKafka {

    @Value(value = "${producerKafka.message.limit}")
    private int limit;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    @EventListener(ApplicationStartedEvent.class)
    public void generate() {
        //new Locale("ru_RU") работает не на все методы
        Faker faker = Faker.instance();
        for (int i = 0; i < limit; i++) {
            kafkaTemplate.send("input",
                    faker.rickAndMorty().character(),
                    faker.rickAndMorty().quote());
            Thread.sleep(1_000);
        }
    }
}
