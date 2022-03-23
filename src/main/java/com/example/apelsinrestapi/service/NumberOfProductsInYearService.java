package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.resNumberOfProductsDTO;
import com.example.apelsinrestapi.entity.Order;
import com.example.apelsinrestapi.repository.CustomerRepository;
import com.example.apelsinrestapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NumberOfProductsInYearService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;

    public ApiResponse getNumberOfProducts() {

        List<Order> ordersIn2016 = new ArrayList<>(); //List of orders in 2016 START
        List<Order> all = orderRepository.findAll();
        for (Order order : all) {
            Date date = order.getDate();
            if (date.before(Date.valueOf("2017-01-01")) && date.after(Date.valueOf("2015-12-31"))){
                ordersIn2016.add(order);
            }
        } //END

        Set<String> set = new HashSet<>(); //All countries in Set START

        for (Order order : ordersIn2016) {
            set.add(order.getCustomer().getCountry());
        }                                  //END

        List<resNumberOfProductsDTO> list = new ArrayList<>(); //Make list of countries and orders START
        String country = "";
        Integer totalNum = 0;
        for (String s : set) {
            for (Order order : ordersIn2016) {
                if (s.equals(order.getCustomer().getCountry())){
                    country = s;
                    totalNum++;
                }
            }
            list.add(new resNumberOfProductsDTO(country,totalNum));
            country = "";
            totalNum = 0;
        }// END

//        Check if there is only a country

        if (list.size() == 1){
            String country1 = list.get(0).getCountry();
            Integer totalNum1 = list.get(0).getTotalNum();
            list.clear();
            list.add(new resNumberOfProductsDTO(totalNum1));
        }

        return new ApiResponse(list);
    }
}
