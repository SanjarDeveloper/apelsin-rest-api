package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.service.ExpiredInvoicesService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/expired_invoices")
@RequiredArgsConstructor
public class ExpiredInvoicesController {
    final ExpiredInvoicesService expiredInvoicesService;

    @GetMapping
    public HttpEntity<?> getExpiredInvoices() throws ParseException {
        ApiResponse expiredInvoices = expiredInvoicesService.getExpiredInvoices();
        return ResponseEntity.ok().body(expiredInvoices.getObject());
    }
}
