package com.RestDemo.RestDemo.repository;

import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.UserDto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class UserRepository {
    List<UserDto> users = new ArrayList<>();
    public UserRepository() {
        users.add(new UserDto(UUID.randomUUID().toString(), "hello", "hello@gmail.com"));
        users.add(new UserDto(UUID.randomUUID().toString(), "hello1", "hello1@gmail.com"));
        users.add(new UserDto(UUID.randomUUID().toString(), "hello2", "hello2@gmail.com"));
    }

    public List<UserDto> findAllUsers(){
        return this.users;
    }
    public UserDto findUserById(String id){
        for(UserDto user:users){
            if(Objects.equals(user.getId(),id)){
                return user;
            }
        }
        return null;
    }
    public UserDto save(CreateUserDto createUser){
        UserDto user = new UserDto(UUID.randomUUID().toString() , createUser.getName(), createUser.getEmail());
        users.add(user);
        return user;
    }
    public UserDto update(CreateUserDto updateUser , String id){
        for(UserDto user : users){
            if(Objects.equals(user.getId(),id)){
                user.setName(updateUser.getName());
                user.setEmail(updateUser.getEmail());
            }
            return  user;
        }
        return null;
    }
    public void delete(String id){
        users.removeIf(userDto ->  userDto.getId().equals(id));
        }
}
