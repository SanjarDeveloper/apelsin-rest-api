package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.OrdersWithoutDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders_without_details")
@RequiredArgsConstructor
public class OrdersWithoutDetailsController {
    final OrdersWithoutDetailsService ordersWithoutDetailsService;
    @GetMapping
    public HttpEntity<?> getOrdersWithoutDetail(){
        ApiResponse ordersWithoutDetails = ordersWithoutDetailsService.getOrdersWithoutDetails();
        return ResponseEntity.ok().body(ordersWithoutDetails.getObject());
    }
}
