package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.OrderDTO;
import com.example.apelsinrestapi.DTO.resOrderDetailsDTO;
import com.example.apelsinrestapi.entity.*;
import com.example.apelsinrestapi.projection.OrderDetailsProjection;
import com.example.apelsinrestapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;
    final InvoiceRepository invoiceRepository;
    final ProductRepository productRepository;
    final DetailRepository detailRepository;

    public ApiResponse add(OrderDTO orderDTO) {
//        Creating order
        Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomer_id());

        Order order = new Order();
        order.setActive(orderDTO.isActive());
        LocalDate now = LocalDate.now();
        order.setDate(Date.valueOf(now));
        if (customer.isPresent()) {
            order.setCustomer(customer.get());
        } else return new ApiResponse("FAILED! (No such customer)", false);

        orderRepository.save(order);

        //        Creating invoice
        Integer orderId = order.getId();

        Optional<Order> orderOptional = orderRepository.findById(orderId);

        Optional<Product> product = productRepository.findById(orderDTO.getProduct_id());
        double amount = 0.00;
        if (product.isPresent()) {
            Double priceForAmount = product.get().getPrice();
            amount = priceForAmount * orderDTO.getQuantity();
        }
        Invoice invoice = new Invoice();
        if (orderOptional.isPresent()) {
            invoice.setOrder(orderOptional.get());
        } else return new ApiResponse("FAILED! (Order not created)", false);
        invoice.setAmount(amount);
        invoice.setDue(Date.valueOf(now.plusDays(3)));
        invoice.setIssued(Date.valueOf(now));

        invoiceRepository.save(invoice);

        Integer invoiceId = invoice.getId();

//        Creating detail

        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);

        Detail detail = new Detail();
        detail.setOrder(orderOptional.get());
        detail.setActive(orderDTO.isActive());
        if (product.isPresent()) {
            detail.setProduct(product.get());
        } else return new ApiResponse("FAILED! (No such product)", false);
        detail.setQuantity(orderDTO.getQuantity());
        detail.setActive(true);
        detailRepository.save(detail);
        return new ApiResponse("status: SUCCESS!", true,"invoice_id: " + invoiceOptional.get().getId());
    }

    public ApiResponse update(Integer id, OrderDTO orderDTO) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("No such order!", false);
        else {
            Optional<Customer> byId1 = customerRepository.findById(orderDTO.getCustomer_id());
            Order order = byId.get();
            if (byId1.isPresent()) order.setCustomer(byId1.get());
            else return new ApiResponse("No such customer", false);
            order.setActive(orderDTO.isActive());
            orderRepository.save(order);
        }
        return new ApiResponse("Updated!", true);
    }

    public ApiResponse getOrderDetailsById(Integer order_id){
        Optional<resOrderDetailsDTO> orderDetails = productRepository.getOrderDetails(order_id);
        if (orderDetails.isPresent()) return new ApiResponse("Here", true, orderDetails);
        else return new ApiResponse("Not Found",false);
    }
}
