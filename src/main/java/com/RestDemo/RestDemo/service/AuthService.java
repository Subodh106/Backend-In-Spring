package com.RestDemo.RestDemo.service;

import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.LoginDto;
import com.RestDemo.RestDemo.dto.LoginResponseDto;
import com.RestDemo.RestDemo.dto.RegisterUserResponseDto;
import com.RestDemo.RestDemo.entities.Role;
import com.RestDemo.RestDemo.entities.User;
import com.RestDemo.RestDemo.repository.UserRepository;
import com.RestDemo.RestDemo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

    public LoginResponseDto LogInUser(LoginDto login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()
                ));
        String JwtToken = jwtService.generateJwtToken((UserDetails) Objects.requireNonNull(authentication.getPrincipal()));
        return new LoginResponseDto(JwtToken);
    }
}
