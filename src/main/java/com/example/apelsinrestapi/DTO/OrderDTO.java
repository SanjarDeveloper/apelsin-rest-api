package com.example.apelsinrestapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Integer customer_id;
    private Integer product_id;
    private Integer quantity;
    private boolean active;
}
