package com.rebakure.bestshoes.services;

import com.rebakure.bestshoes.dtos.CheckoutEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(
            topics = "checkout-topic",
            groupId = "checkouts"
    )
    void checkout(CheckoutEvent checkoutEvent) {
        System.out.println("🚀 Listening on Kafka Listener");
        System.out.println(checkoutEvent.toString());
    }
}
