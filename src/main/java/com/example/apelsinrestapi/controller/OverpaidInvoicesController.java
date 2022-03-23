package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.OverpaidInvoicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overpaid_invoices")
@RequiredArgsConstructor
public class OverpaidInvoicesController {
    final OverpaidInvoicesService overpaidInvoicesService;
@GetMapping
    public HttpEntity<?> getOverpaidInvoices(){
        ApiResponse overpaidInvoices = overpaidInvoicesService.getOverpaidInvoices();
        return ResponseEntity.ok().body(overpaidInvoices.getObject());
    }
}
