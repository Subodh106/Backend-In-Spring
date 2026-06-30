package com.RestDemo.RestDemo.service;
import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.UserDto;
import com.RestDemo.RestDemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){

        this.userRepository = userRepository;
    }
    public List<UserDto> getAllUser(){
        return userRepository.findAllUsers();
    }
    public UserDto getUserById(String id){
        return userRepository.findUserById(id);
    }
    public UserDto createUser(CreateUserDto createUser){
        return userRepository.save(createUser);
    }
    public UserDto updateUser(CreateUserDto updateUser , String id){
        if(!Objects.equals(userRepository.findUserById(id).toString(), id)){
            return null;
        }
        return userRepository.update(updateUser , id);
    }
    public static void deleteUserById(String id){
        userRepository.delete(id);
    }
}
