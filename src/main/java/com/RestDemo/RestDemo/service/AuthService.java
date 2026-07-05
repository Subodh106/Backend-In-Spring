package com.RestDemo.RestDemo.service;

import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.RegisterUserResponseDto;
import com.RestDemo.RestDemo.entities.Role;
import com.RestDemo.RestDemo.entities.User;
import com.RestDemo.RestDemo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public RegisterUserResponseDto RegisterUser(CreateUserDto createUser) {
        User user = new User();

        user.setEmail(createUser.getEmail());
        user.setName(createUser.getName());
        user.setPassword(passwordEncoder.encode(createUser.getPassword()));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return new RegisterUserResponseDto(savedUser.getId(), savedUser.getName());
    }
}
