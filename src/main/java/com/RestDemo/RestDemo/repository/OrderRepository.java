package com.RestDemo.RestDemo.repository;

import com.RestDemo.RestDemo.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findOrderByID(Long userId);

    @Query(
            value = """
                    SELECT *
                    FROM orders
                    WHERE user_id = :userId
                     ORDER BY created_at DESC
                    """,
            countQuery = """
                                 SELECT COUNT(*)
                                FROM orders
                                WHERE user_id = :userId
                    """,
            nativeQuery = true
    )
    Page<Order> findOrdersByUserId(@Param("userId") Long userId, Pageable pageable);
}
