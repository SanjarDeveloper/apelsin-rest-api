package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.resHighDemandProductsDTO;
import com.example.apelsinrestapi.entity.Detail;
import com.example.apelsinrestapi.entity.Product;
import com.example.apelsinrestapi.repository.DetailRepository;
import com.example.apelsinrestapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HighDemandProductsService {
    final DetailRepository detailRepository;
    final ProductRepository productRepository;

    public ApiResponse getHighDemandProducts() {
//        List<Detail> all = detailRepository.findAll();
//        List<resHighDemandProductsDTO> fin = new ArrayList<>();
//        for (int c = 0; c < all.size(); c++) {
//            if(all.get(c).getQuantity() >= 10){
//                fin.add(new resHighDemandProductsDTO(all.get(c).getProduct().getId(),all.get(c).getQuantity()));
//            }
//        }

        Set<Product> productSet = new HashSet<>(productRepository.findAll());
        List<resHighDemandProductsDTO> list = new ArrayList<>();

        Integer allQuantity = 0;
        Integer product_id = 0;
        Iterator<Product> iterator = productSet.iterator();
        while (iterator.hasNext()) {
            for (Detail detail : detailRepository.findByProductId(iterator.next().getId())) {
                allQuantity = allQuantity + detail.getQuantity();
                product_id = detail.getProduct().getId();
            }
            list.add(new resHighDemandProductsDTO(product_id, allQuantity));
            product_id = 0;
            allQuantity = 0;
        }
        return new ApiResponse(list);
    }
}
