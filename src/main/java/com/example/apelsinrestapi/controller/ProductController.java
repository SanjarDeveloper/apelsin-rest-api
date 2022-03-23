package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.ProductDTO;
import com.example.apelsinrestapi.entity.Product;
import com.example.apelsinrestapi.projection.ProductProjection;
import com.example.apelsinrestapi.repository.ProductRepository;
import com.example.apelsinrestapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    final ProductService productService;
    final ProductRepository productRepository;

    @PostMapping
    public HttpEntity<?> Create(@RequestBody ProductDTO productDTO) {
        ApiResponse response = productService.add(productDTO);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> ReadAll(){
        List<ProductProjection> all = productRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> ReadOne(@PathVariable Integer id){
        Optional<Product> byId = productRepository.findById(id);
        return ResponseEntity.ok().body(byId);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> Update(@PathVariable Integer id,@RequestBody ProductDTO productDTO){
        ApiResponse update = productService.update(id, productDTO);
        return ResponseEntity.ok().body(update);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id){
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setActive(false);
            productRepository.save(product);
        } else {
            return ResponseEntity.status(404).body("No such Product!");
        }
        return ResponseEntity.ok().body("Deleted!");
    }
    @GetMapping("/details")
    public HttpEntity<?> getProductDetailsById(@RequestParam Integer product_id){
        ApiResponse productDetailsById = productService.getProductDetailsById(product_id);
        return ResponseEntity.ok().body(productDetailsById.getObject());
    }
}

