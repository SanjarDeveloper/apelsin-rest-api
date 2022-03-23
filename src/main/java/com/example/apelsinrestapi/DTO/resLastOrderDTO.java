package com.example.apelsinrestapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class resLastOrderDTO {
    private Integer customer_id;
    private String customer_name;
    private Date date;
}
