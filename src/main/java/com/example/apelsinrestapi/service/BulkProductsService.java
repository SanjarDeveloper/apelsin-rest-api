package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.resBulkDTO;
import com.example.apelsinrestapi.entity.Detail;
import com.example.apelsinrestapi.entity.Product;
import com.example.apelsinrestapi.repository.DetailRepository;
import com.example.apelsinrestapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BulkProductsService {
    final DetailRepository detailRepository;
    final ProductRepository productRepository;

    public ApiResponse getBulkProducts(){
        Set<Product> productSet = new HashSet<>(productRepository.findAll());
        List<resBulkDTO> list = new ArrayList<>();
        Iterator<Product> iterator = productSet.iterator();
        Integer product_id = 0;
        int count = 0;
        Double price = 0d;
        Double allQuantity = 0d;
        while (iterator.hasNext()){
            for (Detail detail : detailRepository.findByProductId(iterator.next().getId())) {
                   allQuantity = allQuantity + detail.getQuantity();
                   count++;
                   product_id = detail.getProduct().getId();
                   price = detail.getProduct().getPrice();
            }
            Double average = allQuantity / count;
            if(average >= 8) {
                list.add(new resBulkDTO(product_id,price));
            }
            count = 0;
            allQuantity = 0d;
        }
        return new ApiResponse(list);
    }
}
