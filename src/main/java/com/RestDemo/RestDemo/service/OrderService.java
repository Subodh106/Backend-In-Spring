package com.RestDemo.RestDemo.service;
import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import com.RestDemo.RestDemo.dto.UserDto;
import com.RestDemo.RestDemo.entities.Order;
import com.RestDemo.RestDemo.entities.User;
import com.RestDemo.RestDemo.exception.UserNotFoundException;
import com.RestDemo.RestDemo.repository.OrderRepository;
import com.RestDemo.RestDemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder.getID(), savedOrder.getProductName(), savedOrder.getPrice(), new UserDto(savedOrder.getUser().getId(), savedOrder.getUser().getName(), savedOrder.getUser().getEmail()));
    }

    public List<OrderDto> getOrderById(Long userId) {
        List<Order> orders = orderRepository.findOrderByID(userId);
        List<OrderDto> orderDto = new ArrayList<>();

        orders.forEach(order -> {
            orderDto.add(new OrderDto(
                    order.getID(),
                    order.getProductName(),
                    order.getPrice(),
                    new UserDto(order.getUser().getId(),
                            order.getUser().getName(),
                            order.getUser().getEmail()
                    )
            ));
        });

        return orderDto;
    }

    public String deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id :"));
        orderRepository.deleteById(order.getID());
        return "Order deleted Successfully";
    }

    public List<OrderDto> getOrderPaginated(Long userID, int page, int pageSize, String direction, String sortBy) {
        Sort sort;
        sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Order> orderPage = orderRepository.findOrdersByUserId(userID, pageable);
        List<OrderDto> orderDto = new ArrayList<>();
        orderPage.forEach(order -> {
            orderDto.add(
                    new OrderDto(
                            order.getID()
                            , order.getProductName()
                            , order.getPrice()
                            , new UserDto(
                            order.getUser().getId(),
                            order.getUser().getName(),
                            order.getUser().getEmail()
                    )
                    )
            );
        });
        return orderDto;
    }

}
