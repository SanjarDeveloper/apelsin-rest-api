package com.example.apelsinrestapi.DTO;

import java.sql.Date;

public interface resOrderDetailsDTO {
    String getName();
    String getProduct_name();
    Double getProduct_price();
    Integer getQuantity();
    Double getAmount();
    Date getIssued();
    Date getDue();
}
