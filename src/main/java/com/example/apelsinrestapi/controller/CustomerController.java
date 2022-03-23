package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.CustomerDTO;
import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.repository.CustomerRepository;
import com.example.apelsinrestapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    final CustomerService customerService;
    final CustomerRepository customerRepository;

    @PostMapping
    public HttpEntity<?> Create(@RequestBody CustomerDTO customerDTO) {
        ApiResponse response = customerService.add(customerDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public HttpEntity<?> ReadAll() {
        List<Customer> all = customerRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> ReadOne(@PathVariable Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        return ResponseEntity.ok().body(byId);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> Update(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        ApiResponse update = customerService.update(id, customerDTO);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent()) {
            Customer customer = byId.get();
            customer.setActive(false);
            customerRepository.save(customer);
        }
        else return ResponseEntity.status(404).body("No such customer");
        return ResponseEntity.ok().body("Deleted!");
    }
}
