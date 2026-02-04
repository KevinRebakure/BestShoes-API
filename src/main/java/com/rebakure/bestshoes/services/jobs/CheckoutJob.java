package com.rebakure.bestshoes.services.jobs;

import com.rebakure.bestshoes.common.OrderStatus;
import com.rebakure.bestshoes.dtos.CheckoutEvent;
import com.rebakure.bestshoes.repositories.OrderRepository;
import com.rebakure.bestshoes.repositories.VariantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CheckoutJob {
    private  final OrderRepository orderRepository;
    private  final VariantRepository variantRepository;

    @KafkaListener(
            topics = "checkout-topic",
            groupId = "checkouts"
    )
    @Async
    @Transactional
    void checkout(CheckoutEvent checkoutEvent) throws InterruptedException {
        System.out.println("Listening on Kafka Listener");
        System.out.println("Going to sleep for 10 seconds");
        Thread.sleep(10_000);

        System.out.println("Updating the database");
        var order = orderRepository.findById(checkoutEvent.getOrderId()).orElse(null);

        if (order != null) {
            // Update the order status
            order.setStatus(OrderStatus.PAID.toString());
            orderRepository.save(order);

            // Update the inventory
            checkoutEvent.getItems().forEach(item -> {
                var variant = variantRepository.findById(item.getVariantId()).orElse(null);
                if (variant != null) {
                    variant.setQuantity(variant.getQuantity() - item.getQuantity());
                    variantRepository.save(variant);
                }
            });
        }

        System.out.println("Finished execution");
    }
}
