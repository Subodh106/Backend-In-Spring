package com.RestDemo.RestDemo.service;

import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import com.RestDemo.RestDemo.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<CreateOrderDto> createOrder(CreateOrderDto createOrderDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body((CreateOrderDto) orderRepository.create(createOrderDto));
    }
}
