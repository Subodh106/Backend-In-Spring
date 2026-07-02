package com.RestDemo.RestDemo.controller;

import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import com.RestDemo.RestDemo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(createOrderDto));
    }
}
