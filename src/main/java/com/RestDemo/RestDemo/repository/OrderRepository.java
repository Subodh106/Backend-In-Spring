package com.RestDemo.RestDemo.repository;

import com.RestDemo.RestDemo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByID(Long userId);
}
