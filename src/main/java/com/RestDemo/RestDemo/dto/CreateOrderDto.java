package com.RestDemo.RestDemo.dto;

import com.RestDemo.RestDemo.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDto {
    private String productName;
    private float price;
    private User user;

    public CreateOrderDto(String productName, float price, User user) {
        this.productName = productName;
        this.price = price;
        this.user = user;
    }
}
