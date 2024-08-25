package com.example.springkafka.delayedmessage.driver.web;

import com.example.springkafka.delayedmessage.core.domain.OrderEvent;
import com.example.springkafka.delayedmessage.core.port.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final OrderEventPublisher orderEventPublisher;

    @PostMapping("/api/orders")
    public void post() {

        OrderEvent orderEvent = new OrderEvent(UUID.randomUUID(), LocalDateTime.now());
        orderEventPublisher.publish(orderEvent);
    }
}
