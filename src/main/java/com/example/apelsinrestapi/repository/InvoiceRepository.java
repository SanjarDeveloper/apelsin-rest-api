package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Invoice;
import com.example.apelsinrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
