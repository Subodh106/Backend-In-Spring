package com.RestDemo.RestDemo.service;

import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import com.RestDemo.RestDemo.entities.Order;
import com.RestDemo.RestDemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        order.setProductName(createOrderDto.getProductName());
        order.setPrice(createOrderDto.getPrice());
        order.setUser(createOrderDto.getUser());
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder.getID(), savedOrder.getProductName(), savedOrder.getPrice(), savedOrder.getUser());
    }
}
