package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.PaymentDTO;
import com.example.apelsinrestapi.DTO.resPaymentDTO;
import com.example.apelsinrestapi.entity.*;
import com.example.apelsinrestapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    final PaymentRepository paymentRepository;
    final InvoiceRepository invoiceRepository;
    final DetailRepository detailRepository;

    public ApiResponse add(PaymentDTO paymentDTO) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(paymentDTO.getInvoice_id());
        Payment payment = new Payment();
        payment.setActive(true);
        payment.setTime(Timestamp.valueOf(LocalDateTime.now()));
        payment.setAmount(paymentDTO.getAmount());
        if (invoiceOptional.isPresent()) {
            payment.setInvoice(invoiceOptional.get());
        } else return new ApiResponse("No such Invoice!", false);
        paymentRepository.save(payment);
        Integer paymentId = payment.getId();
        Optional<Payment> byId = paymentRepository.findById(paymentId);
        resPaymentDTO resPaymentDTO = paymentTOresPaymentDTO(byId.get());
        return new ApiResponse("SUCCESS", true, resPaymentDTO);

    }

    //    "Payment_status: " + response.getMessage() + "\n" + paymentRepository.findAllByInvoiceId(paymentDTO.getInvoice_id())
    public ApiResponse update(Integer id, PaymentDTO paymentDTO) {
        return new ApiResponse("Nothing Updated!", true);
    }

    public ApiResponse getPaymentDetails(Integer payment_id){
        Optional<Payment> byId = paymentRepository.findById(payment_id);
        resPaymentDTO resPaymentDTO = paymentTOresPaymentDTO(byId.get());
        return new ApiResponse("Here",true,resPaymentDTO);
    }

    public static resPaymentDTO paymentTOresPaymentDTO(Payment payment) {
        return new resPaymentDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getTime(),
                payment.getInvoice().getId()
        );
    }
}