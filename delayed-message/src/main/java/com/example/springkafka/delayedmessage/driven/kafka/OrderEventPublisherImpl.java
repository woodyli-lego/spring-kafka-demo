package com.example.springkafka.delayedmessage.driven.kafka;

import com.example.springkafka.delayedmessage.core.domain.OrderEvent;
import com.example.springkafka.delayedmessage.core.port.OrderEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventPublisherImpl implements OrderEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;


    @SneakyThrows
    @Override
    public void publish(OrderEvent orderEvent) {
        String message = objectMapper.writeValueAsString(orderEvent);
        kafkaTemplate.send("web.orders", message);
    }
}
