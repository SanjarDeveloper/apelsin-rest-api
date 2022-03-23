package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.entity.Detail;
import com.example.apelsinrestapi.entity.Order;
import com.example.apelsinrestapi.repository.DetailRepository;
import com.example.apelsinrestapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersWithoutDetailsService {
    final OrderRepository orderRepository;
    final DetailRepository detailRepository;

    public ApiResponse getOrdersWithoutDetails(){
        List<Order> ListWithoutDetails = orderRepository.findAll();

        List<Order> ListWithoutDetailsAndBefore2016 = new ArrayList<>();

        for (Order order : orderRepository.findAll()) {
            for (Detail detail : detailRepository.findAll()) {
                if (order.getId().equals(detail.getOrder().getId())){
                    ListWithoutDetails.remove(order);
                }
            }
        }
        Date date = Date.valueOf("2016-09-6");
        for (Order listWithoutDetail : ListWithoutDetails) {
            if (listWithoutDetail.getDate().before(date)){
                ListWithoutDetailsAndBefore2016.add(listWithoutDetail);
            }
        }


        return new ApiResponse(ListWithoutDetailsAndBefore2016);
    }

}
