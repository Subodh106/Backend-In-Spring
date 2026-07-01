package com.RestDemo.RestDemo.dto;

import com.RestDemo.RestDemo.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private String productName;
    private float price;
    private User user;

    public OrderDto(Long id, String productName, float price, User user) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.user = user;
    }
}
