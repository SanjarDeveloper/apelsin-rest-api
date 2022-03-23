package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.NumberOfProductsInYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number_of_products_in_year")
@RequiredArgsConstructor
public class numberOfProductsInYearController {
    final NumberOfProductsInYearService numberOfProductsInYearService;
@GetMapping
    public HttpEntity<?> getProductsInYear(){
        ApiResponse numberOfProducts = numberOfProductsInYearService.getNumberOfProducts();
        return ResponseEntity.ok().body(numberOfProducts.getObject());
    }
}
