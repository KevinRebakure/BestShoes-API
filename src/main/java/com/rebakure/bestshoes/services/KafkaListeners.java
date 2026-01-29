package com.rebakure.bestshoes.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(
            topics = "checkout-topic",
            groupId = "checkouts"
    )
    void checkout() {
        System.out.println("Listening on Kafka Listener");
    }
}
