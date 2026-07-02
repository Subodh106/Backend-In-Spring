package com.RestDemo.RestDemo.repository;

import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class OrderRepository {
    List<OrderDto> orders = new ArrayList<>();

    public OrderDto Create(CreateOrderDto createOrderDto) {
        System.out.println(createOrderDto);
        return (orders.get(1));
    }
}
