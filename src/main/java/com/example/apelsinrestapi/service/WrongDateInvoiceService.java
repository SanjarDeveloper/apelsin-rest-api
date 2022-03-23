package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.resWrongInvoiceDTO;
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
public class WrongDateInvoiceService {
    final InvoiceRepository invoiceRepository;

    public ApiResponse getWrongInvoices() throws ParseException {
        List<resWrongInvoiceDTO> ListOfWrongInvoices = new ArrayList<>();

        for (Invoice invoice : invoiceRepository.findAll()) {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdformat.parse(String.valueOf(invoice.getIssued()));
            Date d2 = sdformat.parse(String.valueOf(invoice.getOrder().getDate()));
            if (d1.before(d2)){
                resWrongInvoiceDTO resWrongInvoiceDTO = invoiceTOresInvoiceDTO(invoice);
                ListOfWrongInvoices.add(resWrongInvoiceDTO);
            }
        }
        return new ApiResponse(ListOfWrongInvoices);
    }
    public static resWrongInvoiceDTO invoiceTOresInvoiceDTO(Invoice invoice){
        return new resWrongInvoiceDTO(
                invoice.getId(),
                invoice.getIssued(),
                invoice.getOrder().getId()
        );
    }
}
