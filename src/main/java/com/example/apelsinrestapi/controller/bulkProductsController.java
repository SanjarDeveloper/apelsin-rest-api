package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.BulkProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bulk_products")
@RequiredArgsConstructor
public class bulkProductsController {
    final BulkProductsService bulkProductsService;
    @GetMapping
    public HttpEntity<?> getBulkProducts(){
        ApiResponse bulkProducts = bulkProductsService.getBulkProducts();
        return ResponseEntity.ok().body(bulkProducts.getObject());
    }
}
