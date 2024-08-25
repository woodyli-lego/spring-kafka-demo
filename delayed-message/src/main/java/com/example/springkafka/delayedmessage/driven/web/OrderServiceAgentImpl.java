package com.example.springkafka.delayedmessage.driven.web;

import com.example.springkafka.delayedmessage.core.domain.OrderEvent;
import com.example.springkafka.delayedmessage.core.port.OrderServiceAgent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderServiceAgentImpl implements OrderServiceAgent {

    @Override
    public OrderEvent.Status findOrderStatus(UUID orderId) {
        return OrderEvent.Status.PROCESSED;
    }
}
