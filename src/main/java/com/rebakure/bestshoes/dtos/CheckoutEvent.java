package com.rebakure.bestshoes.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutEvent {
    private Long orderId;
    private Long userId;
    private List<OrderItemSummary> items;
}

