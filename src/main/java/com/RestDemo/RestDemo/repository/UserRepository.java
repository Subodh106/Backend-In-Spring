package com.RestDemo.RestDemo.repository;

import com.RestDemo.RestDemo.entities.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @NullMarked
    Page<User> findAll(Pageable pageable);
}
