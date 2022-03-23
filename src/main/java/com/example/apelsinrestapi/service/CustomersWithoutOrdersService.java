package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.entity.Order;
import com.example.apelsinrestapi.repository.CustomerRepository;
import com.example.apelsinrestapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomersWithoutOrdersService {
    final CustomerRepository customerRepository;
    final OrderRepository orderRepository;

    public ApiResponse getCustomersWithoutOrders(){
        List<Customer> customerList = customerRepository.findAll();

        Date date2017 = Date.valueOf("2017-01-01");
        Date date2015 = Date.valueOf("2015-12-31");

        for (Order order : orderRepository.findAll()) {
            if (order.getDate().before(date2017) && order.getDate().after(date2015)){
                customerList.remove(order.getCustomer());
            }
        }

        return new ApiResponse(customerList);
    }
}
