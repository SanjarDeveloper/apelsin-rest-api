package com.example.apelsinrestapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class resWrongInvoiceDTO {
    private Integer invoice_id;
    private Date invoice_issued;
    private Integer order_id;
}
