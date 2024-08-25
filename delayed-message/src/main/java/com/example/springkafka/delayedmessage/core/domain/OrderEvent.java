package com.example.springkafka.delayedmessage.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderEvent(
        UUID orderId,
        LocalDateTime generatedAt
) {

    public enum Status {
        PENDING,
        PROCESSED
    }
}
