package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.entity.Invoice;
import com.example.apelsinrestapi.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpiredInvoicesService {
    final InvoiceRepository invoiceRepository;

    public ApiResponse getExpiredInvoices() throws ParseException {
        List<Invoice> ListOfExpiredInvoices = new ArrayList<>();

        for (Invoice invoice : invoiceRepository.findAll()) {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdformat.parse(String.valueOf(invoice.getDue()));
            Date d2 = sdformat.parse(String.valueOf(LocalDate.now()));
            if (d1.before(d2)){
                ListOfExpiredInvoices.add(invoice);
            }
        }
        return new ApiResponse(ListOfExpiredInvoices);
    }
}
