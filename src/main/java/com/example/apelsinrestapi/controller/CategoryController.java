package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.CategoryDTO;
import com.example.apelsinrestapi.entity.Category;
import com.example.apelsinrestapi.projection.CategoryProjection;
import com.example.apelsinrestapi.repository.CategoryRepository;
import com.example.apelsinrestapi.repository.ProductRepository;
import com.example.apelsinrestapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;

    @PostMapping
    public HttpEntity<?> Create(@RequestBody CategoryDTO categoryDTO) {
        ApiResponse response = categoryService.add(categoryDTO);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/list")
    public HttpEntity<?> ReadAll(){
        List<CategoryProjection> all = categoryRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> ReadOne(@PathVariable Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        return ResponseEntity.ok().body(byId);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> Update(@PathVariable Integer id,@RequestBody CategoryDTO categoryDTO){
        ApiResponse update = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(update);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setActive(false);
            categoryRepository.save(category);
        } else {
            return ResponseEntity.status(404).body("No such category");
        }
        return ResponseEntity.ok().body("Deleted!");
    }
    @GetMapping
    public HttpEntity<?> getProductCategoryById(@RequestParam Integer product_id){
        ApiResponse productCategoryById = categoryService.getProductCategoryById(product_id);
        return ResponseEntity.ok().body(productCategoryById);
    }
}
