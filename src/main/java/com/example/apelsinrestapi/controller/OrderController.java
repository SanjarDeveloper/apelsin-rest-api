package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.OrderDTO;
import com.example.apelsinrestapi.entity.Order;
import com.example.apelsinrestapi.repository.OrderRepository;
import com.example.apelsinrestapi.repository.ProductRepository;
import com.example.apelsinrestapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    final OrderService orderService;
    final OrderRepository orderRepository;
    final ProductRepository productRepository;

    @PostMapping
    public HttpEntity<?> Create(@RequestBody OrderDTO orderDTO) {
        ApiResponse response = orderService.add(orderDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public HttpEntity<?> ReadAll() {
        List<Order> all = orderRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> ReadOne(@PathVariable Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        return ResponseEntity.ok().body(byId);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> Update(@PathVariable Integer id, @RequestBody OrderDTO customerDTO) {
        ApiResponse update = orderService.update(id, customerDTO);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            Order order = byId.get();
            order.setActive(false);
            orderRepository.save(order);
        } else return ResponseEntity.status(404).body("No such order");
        return ResponseEntity.ok().body("Deleted!");
    }
    @GetMapping("/details")
    public HttpEntity<?> getOrderDetailsById(@RequestParam Integer order_id){
        ApiResponse response = orderService.getOrderDetailsById(order_id);
        return ResponseEntity.ok().body(response.getObject());
    }

}
