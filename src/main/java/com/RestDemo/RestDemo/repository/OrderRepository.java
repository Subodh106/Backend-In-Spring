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

    public List<OrderDto> create(CreateOrderDto createOrderDto) {
//        orders.add(new OrderDto(1L , "laptop",188.88 ,"") )
        System.out.println(createOrderDto);
        return Collections.singletonList(orders.get(1));
    }
}
