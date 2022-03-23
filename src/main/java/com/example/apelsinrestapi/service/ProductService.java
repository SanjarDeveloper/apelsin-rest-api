package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.PaymentDTO;
import com.example.apelsinrestapi.DTO.ProductDTO;
import com.example.apelsinrestapi.entity.*;
import com.example.apelsinrestapi.projection.ProductDetailsProjection;
import com.example.apelsinrestapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final InvoiceRepository invoiceRepository;
    final CategoryRepository categoryRepository;
    final DetailRepository detailRepository;

    public ApiResponse add(ProductDTO productDTO) {
        Optional<Category> category = categoryRepository.findById(productDTO.getCategory_id());

        Product product = new Product();
        product.setActive(productDTO.isActive());
        if(category.isPresent()) product.setCategory(category.get());
        else return new ApiResponse("No such Category!",false);
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setPhoto(productDTO.getPhoto());
        productRepository.save(product);
        return new ApiResponse("Added!",true);
    }
    public ApiResponse update(Integer id, ProductDTO productDTO){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) return new ApiResponse("No such Product!",false);
        else {
            Optional<Category> category = categoryRepository.findById(productDTO.getCategory_id());
            Product product = productOptional.get();
            product.setPhoto(productDTO.getPhoto());
            product.setActive(productDTO.isActive());
            if (category.isPresent()) product.setCategory(category.get());
            else return new ApiResponse("No such Category!",false);
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setName(productDTO.getName());
            productRepository.save(product);
        }
        return new ApiResponse("Updated!",true);
    }
    public ApiResponse getProductDetailsById(Integer product_id){
        Optional<ProductDetailsProjection> allById = productRepository.findAllById(product_id);
        if (allById.isEmpty()) return new ApiResponse("Not found!",false);
            else return new ApiResponse("Here",true,allById);
    }
}
