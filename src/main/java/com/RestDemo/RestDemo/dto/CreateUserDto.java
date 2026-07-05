package com.RestDemo.RestDemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreateUserDto {
    @NotBlank
    @NotNull
    @Size(max = 8)
    private String name;
    @NotBlank
    @NotNull
    private String email;
    @NotNull
    @NotBlank
    private String password;

}
