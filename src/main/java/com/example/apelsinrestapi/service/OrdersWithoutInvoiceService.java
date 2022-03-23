package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.resOrdersWithoutDetailsDTO;
import com.example.apelsinrestapi.entity.Detail;
import com.example.apelsinrestapi.entity.Invoice;
import com.example.apelsinrestapi.entity.Order;
import com.example.apelsinrestapi.repository.DetailRepository;
import com.example.apelsinrestapi.repository.InvoiceRepository;
import com.example.apelsinrestapi.repository.OrderRepository;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersWithoutInvoiceService {
    final InvoiceRepository invoiceRepository;
    final OrderRepository orderRepository;
    final DetailRepository detailRepository;

    public ApiResponse getOrdersWithoutInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<Order> orderList = orderRepository.findAll();

        for (Invoice invoice : invoiceList) { // Orders without invoices List START
            orderList.removeIf(order -> invoice.getOrder().getId().equals(order.getId()));
        } //END

        List<Detail> allDetails = detailRepository.findAll();

        List<resOrdersWithoutDetailsDTO> resList = new ArrayList<>();
        Integer order_id = null;
        Date order_date = null;
        Double total_price = 0d;
        for (Order order : orderList) {
            for (Detail allDetail : allDetails) {
                if (allDetail.getOrder().getId().equals(order.getId())){
                    order_id = order.getId();
                    order_date = order.getDate();
                    total_price = (allDetail.getQuantity() * allDetail.getProduct().getPrice()) + total_price;
                }
            }
            resList.add(new resOrdersWithoutDetailsDTO(order_id,order_date,total_price));
            order_id = null;
            order_date = null;
            total_price = 0d;
        }

        return new ApiResponse(nullRemover(resList));
    }
    public static List<resOrdersWithoutDetailsDTO> nullRemover(List<resOrdersWithoutDetailsDTO> list){
        list.removeIf(res -> (res.getOrder_id() == null) || (res.getOrder_date() == null || res.getTotal_price() == null));
        return list;
    }
}
