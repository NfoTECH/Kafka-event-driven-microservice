package com.fortunate.orderservice.controller;

import com.fortunate.basedomains.dto.Order;
import com.fortunate.basedomains.dto.OrderEvent;
import com.fortunate.orderservice.kafka.OrderProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        OrderEvent event = new OrderEvent(
                "Order Placed and is being reviewed",
                "PENDING",
                order
        );
        orderProducer.sendMessage(event);
        return "Order Placed Successfully";
    }
}
