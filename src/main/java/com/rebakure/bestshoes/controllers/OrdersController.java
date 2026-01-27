package com.rebakure.bestshoes.controllers;

import com.rebakure.bestshoes.dtos.*;
import com.rebakure.bestshoes.services.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Validated
@Tag(name = "Orders")
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    @Operation(
            summary = "Create an order",
            description = "This is essentially like creating a cart and later on you can add items to it via POST /orders/{id}/add-item"
    )
    public ResponseEntity<OrderDto> addOrder(
            @Valid @RequestBody OrderRequest request,
            UriComponentsBuilder uriBuilder) {
        var dto = ordersService.addOrder(request);

        var uri = uriBuilder.path("/orders/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @PostMapping("/{id}/checkout")
    @Operation(
            summary = "Checkout for this order",
            description = "This is for making payments and confirming"
    )
    public ResponseEntity<CheckoutResponse> checkout(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        var checkoutResponse = ordersService.checkout(id);
        return ResponseEntity.ok(checkoutResponse);
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
    public ResponseEntity<Void> deleteOrder(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // Order items

    @PostMapping("/{id}/add-item")
    public ResponseEntity<OrderItemDto> addOrderItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id,
            @Valid @RequestBody OrderItemRequest request) {
        var dto = ordersService.addItemToOrder(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PatchMapping("/{id}/items/{itemId}")
    @Operation(
            summary = "Update the number of items of a product you want to order",
            description = "We don't allow swapping products of a particular order item. You can only increase or decrease the number of items you want. Pass the amount (positive / negative) in the body as the quantity. A negative number means you are reducing the number of products you want in that particular item"
    )
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
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable
            @Min(value = 1, message = "id should be a positive integer")
            Long id
    ) {
        ordersService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
