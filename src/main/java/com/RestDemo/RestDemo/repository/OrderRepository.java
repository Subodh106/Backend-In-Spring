package com.RestDemo.RestDemo.repository;

import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import com.RestDemo.RestDemo.dto.UserDto;
import com.RestDemo.RestDemo.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {
    List<OrderDto> orders = new ArrayList<>();

    public List<OrderDto> create(CreateOrderDto createOrderDto) {
//        orders.add(new OrderDto(1L , "laptop",188.88 ,"") )
        return Collections.singletonList(orders.get(1));
    }
}
