package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.CustomersWithoutOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers_without_orders")
@RequiredArgsConstructor
public class CustomersWithoutOrdersController {
    final CustomersWithoutOrdersService customersWithoutOrdersService;

    @GetMapping
    public HttpEntity<?> getCustomersWithoutOrders(){
        ApiResponse customersWithoutOrders = customersWithoutOrdersService.getCustomersWithoutOrders();
        return ResponseEntity.ok().body(customersWithoutOrders.getObject());
    }
}
