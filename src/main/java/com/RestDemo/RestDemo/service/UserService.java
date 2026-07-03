package com.RestDemo.RestDemo.service;
import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.UserDto;
import com.RestDemo.RestDemo.entities.User;
import com.RestDemo.RestDemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

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
        User user = new User();
        user.setName(createUser.getName());
        user.setEmail(createUser.getEmail());
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public UserDto updateUser(CreateUserDto updateUser , String id){
        if(!Objects.equals(userRepository.findUserById(id).toString(), id)){
            return null;
        }
        return userRepository.update(updateUser , id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
