package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.CategoryDTO;
import com.example.apelsinrestapi.entity.Category;
import com.example.apelsinrestapi.entity.Product;
import com.example.apelsinrestapi.projection.ProductProjection;
import com.example.apelsinrestapi.repository.CategoryRepository;
import com.example.apelsinrestapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;

    public ApiResponse add(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setActive(categoryDTO.isActive());
        categoryRepository.save(category);
        return new ApiResponse("Added!",true);
    }
    public ApiResponse update(Integer id,CategoryDTO categoryDTO){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("No such id!",false);
        else {
            Category category = byId.get();
            category.setActive(categoryDTO.isActive());
            category.setName(categoryDTO.getName());
            categoryRepository.save(category);
        }
        return new ApiResponse("Updated!",true);
    }
    public ApiResponse getProductCategoryById(Integer product_id){
        Optional<Product> product = productRepository.findById(product_id);
        if (product.isPresent()) {
            Category category = product.get().getCategory();
            return new ApiResponse("SUCCESS",true,category.getName());
        }
        else return new ApiResponse("No such Product",false);
    }
}
