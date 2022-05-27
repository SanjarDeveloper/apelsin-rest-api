package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders_without_invoices")
@RequiredArgsConstructor
public class OrdersWithoutInvoiceController {
    final OrdersWithoutInvoiceService ordersWithoutInvoiceService;
@GetMapping
    public HttpEntity<?> getOrdersWithoutInvoice(){
        ApiResponse ordersWithoutInvoices = ordersWithoutInvoiceService.getOrdersWithoutInvoices();
        return ResponseEntity.ok().body(ordersWithoutInvoices.getObject());
    }
}
