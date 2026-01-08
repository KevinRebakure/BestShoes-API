package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.*;
import com.rebakure.bestshoes.services.OrdersService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Validated
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@Valid @RequestBody OrderRequest request) {
        var dto = ordersService.addOrder(request);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(ordersService.findOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(
            @RequestParam(required = false) Long userId
    ) {
        return ResponseEntity.ok().body(ordersService.findAllOrders(userId));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        ordersService.deleteOrder(id);
    }

    // Order items

    @PostMapping("/{id}/add-item")
    public ResponseEntity<OrderItemDto> addOrderItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @Valid @RequestBody OrderItemRequest request) {
        var dto = ordersService.addItemToOrder(id, request);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}/items/{itemId}")
    public ResponseEntity<OrderItemDto> updateOrderItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long itemId,
            @Valid @RequestBody UpdateOrderItemRequest request) {
        return ResponseEntity.ok().body(ordersService.updateOrderItem(id, itemId, request));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<OrderItemDto> getOrderItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(ordersService.findOrderItemById(id));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        return ResponseEntity.ok().body(ordersService.findAllOrderItems(id));
    }

    @DeleteMapping("/items/{id}")
    public void deleteOrderItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        ordersService.deleteOrderItem(id);
    }
}
