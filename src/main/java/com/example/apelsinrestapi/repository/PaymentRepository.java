package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByActiveTrue();

    List<Payment> findByInvoiceId(Integer invoice_id);

    Optional<Payment> findById(Integer payment_id);
}
