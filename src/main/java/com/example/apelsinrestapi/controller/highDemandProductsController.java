package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.HighDemandProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/high_demand_products")
@RequiredArgsConstructor
public class highDemandProductsController {
    final HighDemandProductsService highDemandProductsService;
    @GetMapping
    public HttpEntity<?> getHighDemandProducts(){
        ApiResponse highDemandProducts = highDemandProductsService.getHighDemandProducts();
        return ResponseEntity.ok().body(highDemandProducts.getObject());
    }
}
