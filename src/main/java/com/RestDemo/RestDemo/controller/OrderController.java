package com.RestDemo.RestDemo.controller;

import com.RestDemo.RestDemo.dto.CreateOrderDto;
import com.RestDemo.RestDemo.dto.OrderDto;
import com.RestDemo.RestDemo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/{userId}/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId, @RequestBody CreateOrderDto createOrderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(createOrderDto, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrder(id));

    }

    @GetMapping("/paginate")
    public ResponseEntity<List<OrderDto>> getOrderPaginated(@RequestParam Long userId, @RequestParam int page, @RequestParam int pagesSize, @RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderPaginated(userId, page, pagesSize, direction, sortBy));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(userId));
    }

}
