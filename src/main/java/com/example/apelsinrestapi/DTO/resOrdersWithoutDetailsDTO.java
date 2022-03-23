package com.example.apelsinrestapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class resOrdersWithoutDetailsDTO {
    private Integer order_id;
    private Date order_date;
    private Double total_price;
}
