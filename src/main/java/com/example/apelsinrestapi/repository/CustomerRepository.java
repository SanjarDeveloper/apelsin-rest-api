package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByActiveTrue();
}
