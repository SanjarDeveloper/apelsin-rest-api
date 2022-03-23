package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.resLastOrderDTO;
import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.entity.Order;
import com.example.apelsinrestapi.repository.CustomerRepository;
import com.example.apelsinrestapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.sql.Date;
import java.util.*;


@Service
@RequiredArgsConstructor
public class CustomersLastOrdersService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;

    public ApiResponse getCustomersLatOrders() {
        Set<Customer> OrderedMoreThanOne = new HashSet<>(); // START make list of customers who did more than one order
        for (Customer customer : customerRepository.findAll()) {
            List<Customer> customers = new ArrayList<>();
            for (Order order : orderRepository.findAll()) {
                if (order.getCustomer().getId().equals(customer.getId())) {
                    customers.add(order.getCustomer());
                }
            }
            if (customers.size() >= 2) {
                OrderedMoreThanOne.addAll(customers);
            }
        } //END
        List<Order> LastOrders = new ArrayList<>();
        Iterator<Customer> iterator = OrderedMoreThanOne.iterator();
        while (iterator.hasNext()){
            Order lastorder = new Order();
            List<Order> allByCustomerId = orderRepository.findAllByCustomerId(iterator.next().getId());
            lastorder.setDate(allByCustomerId.get(0).getDate());
            for (int c = 0; c < allByCustomerId.size()-1; c++) {
                if (lastorder.getDate().before(allByCustomerId.get(c+1).getDate())){
                    lastorder = allByCustomerId.get(c+1);
                }
            }
            LastOrders.add(lastorder);
        }

        return new ApiResponse(orderTOOrderDTO(LastOrders));
    }

    public static List<resLastOrderDTO> orderTOOrderDTO(List<Order> orderList) {
        List<resLastOrderDTO> list = new ArrayList<>();
        for (Order order : orderList) {
            list.add(new resLastOrderDTO(order.getCustomer().getId(),order.getCustomer().getName(),order.getDate()));
        }
        return list;
    }
}
