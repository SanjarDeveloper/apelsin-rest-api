package com.example.apelsinrestapi.DTO;

import com.example.apelsinrestapi.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class resInvoiceDTO {
    private Integer invoice_id;
    private Double invoice_amount;
    private List<Payment> payment;
}
