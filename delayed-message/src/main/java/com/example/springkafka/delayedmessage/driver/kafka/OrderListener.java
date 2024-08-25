package com.example.springkafka.delayedmessage.driver.kafka;

import com.example.springkafka.delayedmessage.core.domain.OrderEvent;
import com.example.springkafka.delayedmessage.core.port.OrderServiceAgent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.listener.KafkaBackoffException;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "web.orders", groupId = "order-listener")
@RetryableTopic(attempts = "1", include = KafkaBackoffException.class, dltStrategy = DltStrategy.NO_DLT)
public class OrderListener {

    private final ObjectMapper objectMapper;
    private final OrderServiceAgent orderServiceAgent;

    @KafkaHandler
    public void handleOrder(String message) throws JsonProcessingException {

        OrderEvent orderEvent = objectMapper.readValue(message, OrderEvent.class);
        log.info("Processing order: {}", orderEvent);

        OrderEvent.Status status = orderServiceAgent.findOrderStatus(orderEvent.orderId());
        if (status == OrderEvent.Status.PROCESSED) {
            log.info("Order is ready to sync. [orderId={}]", orderEvent.orderId());
        } else {
            log.info("Order is still pending, to be retried. [orderId={}]", orderEvent.orderId());
        }
    }
}
