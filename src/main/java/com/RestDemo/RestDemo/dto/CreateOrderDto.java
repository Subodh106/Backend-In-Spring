package com.RestDemo.RestDemo.dto;

import com.RestDemo.RestDemo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateOrderDto {
    private String productName;
    private float price;
    private UserDto user;
}
