package com.RestDemo.RestDemo.service;
import com.RestDemo.RestDemo.dto.CreateUserDto;
import com.RestDemo.RestDemo.dto.UserDto;
import com.RestDemo.RestDemo.entities.User;
import com.RestDemo.RestDemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDto = new ArrayList<>();

        users.forEach(user -> {
            userDto.add(new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            ));
        });
        return userDto;

    }

    public UserDto getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UserDto(existingUser.getId(), existingUser.getName(), existingUser.getEmail());
    }
    @Transactional
    public UserDto updateUser(CreateUserDto updateUser, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!Objects.equals(existingUser.getId(), id)) {
            return null;
        }
        existingUser.setName(updateUser.getName());
        existingUser.setEmail(updateUser.getEmail());

        return new UserDto(existingUser.getId(), existingUser.getName(), existingUser.getEmail());
    }

    @Transactional
    public UserDto patchUser(CreateUserDto updateUser, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!Objects.equals(existingUser.getId(), id)) {
            return null;
        }
        if (updateUser.getName() != null) {
            existingUser.setName(updateUser.getName());
        }
        if (updateUser.getEmail() != null) {
            existingUser.setEmail(updateUser.getName());
        }
        return new UserDto(existingUser.getId(), existingUser.getName(), existingUser.getEmail());
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.deleteById(user.getId());
    }

    public List<UserDto> getUsersPaginated(int page, int pageSize, String direction, String sortBy) {
        Sort sort;
        sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<User> usersPage = userRepository.findAll(pageable);
        List<UserDto> userDto = new ArrayList<>();
        usersPage.forEach(user -> {
            userDto.add(new UserDto(user.getId(), user.getName(), user.getEmail()));
        });
        return userDto;

    }
}
