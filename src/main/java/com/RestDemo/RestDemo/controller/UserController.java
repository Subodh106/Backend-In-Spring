package com.RestDemo.RestDemo.controller;
import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.UserDto;
import com.RestDemo.RestDemo.entities.User;
import com.RestDemo.RestDemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody CreateUserDto updateUser, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateUser , id));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser(@RequestBody CreateUserDto updateUser, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.patchUser(updateUser, id));
    }

    @GetMapping("/paginate")
    public ResponseEntity<List<UserDto>> getUserPaginated(@RequestParam int page, @RequestParam int pageSize, @RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersPaginated(page, pageSize, direction, sortBy));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
