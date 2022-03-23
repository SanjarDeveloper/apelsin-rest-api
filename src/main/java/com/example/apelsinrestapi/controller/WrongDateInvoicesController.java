package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.WrongDateInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/wrong_date_invoices")
@RequiredArgsConstructor
public class WrongDateInvoicesController {
    final WrongDateInvoiceService wrongDateInvoiceService;

    @GetMapping
    public HttpEntity<?> getWrongInvoices() throws ParseException {
        ApiResponse wrongInvoices = wrongDateInvoiceService.getWrongInvoices();
        return ResponseEntity.ok().body(wrongInvoices.getObject());
    }
}
