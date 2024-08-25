package com.example.springkafka.delayedmessage.core.port;

import com.example.springkafka.delayedmessage.core.domain.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent orderEvent);
}
