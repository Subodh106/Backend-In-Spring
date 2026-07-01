package com.RestDemo.RestDemo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserDto {
    private String name;
    private String email;

    public CreateUserDto( String name , String email) {
        this.name = name;
        this.email = email;
    }
}
