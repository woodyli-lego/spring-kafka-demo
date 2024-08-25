package com.example.springkafka.delayedmessage.core.port;

import com.example.springkafka.delayedmessage.core.domain.OrderEvent;

import java.util.UUID;

public interface OrderServiceAgent {

    OrderEvent.Status findOrderStatus(UUID orderId);
}
