package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Order;
import com.example.apelsinrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByActiveTrue();

    List<Order> findAllByCustomerId(Integer customer_id);
}
