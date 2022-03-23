package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.CustomersLastOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers_last_orders")
public class CustomersLastOrdersController {
    final CustomersLastOrdersService customersLastOrdersService;
    @GetMapping
    public HttpEntity<?> getCustomerLastOrder(){
        ApiResponse customersLatOrders = customersLastOrdersService.getCustomersLatOrders();
        return ResponseEntity.ok().body(customersLatOrders.getObject());
    }
}
