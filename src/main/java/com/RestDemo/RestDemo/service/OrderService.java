package com.RestDemo.RestDemo.service;

import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import com.RestDemo.RestDemo.entities.Order;
import com.RestDemo.RestDemo.entities.User;
import com.RestDemo.RestDemo.repository.OrderRepository;
import com.RestDemo.RestDemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderDto createOrder(CreateOrderDto createOrderDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Order order = new Order();
        order.setProductName(createOrderDto.getProductName());
        order.setPrice(createOrderDto.getPrice());
        order.setUser(createOrderDto.getUser());
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder.getID(), savedOrder.getProductName(), savedOrder.getPrice(), savedOrder.getUser());
    }

    public List<OrderDto> getOrderById(Long userId) {
        List<Order> orders = orderRepository.findOrderByID(userId);
        List<OrderDto> orderDto = new ArrayList<>();

        orders.forEach(order -> {
            orderDto.add(new OrderDto(
                    order.getID(),
                    order.getProductName(),
                    order.getPrice(),
                    order.getUser()
            ));
        });

        return orderDto;
    }

}
