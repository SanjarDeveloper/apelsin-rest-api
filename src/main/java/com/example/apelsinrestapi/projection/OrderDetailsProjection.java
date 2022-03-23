package com.example.apelsinrestapi.projection;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;

public interface OrderDetailsProjection {
    @Value("#{target.product.name}")
    String getProductName();

    @Value("#{target.product.price}")
    Double getProductPrice();

    @Value("#{target_customer.name}")
    String getCustomerName();

//    @Value("#{taget.detail.quantity}")
//    Integer getQuantity();
//
//    @Value("#{target.invoice.issued}")
//    Date getIssued();
//
//    @Value("#{target.invoice.due}")
//    Date getDue();


}
