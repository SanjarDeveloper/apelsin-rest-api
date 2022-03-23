package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.PaymentDTO;
import com.example.apelsinrestapi.entity.Payment;
import com.example.apelsinrestapi.repository.PaymentRepository;
import com.example.apelsinrestapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    final PaymentService paymentService;
    final PaymentRepository paymentRepository;

    @PostMapping
    public HttpEntity<?> Create(@RequestBody PaymentDTO paymentDTO) {
        ApiResponse response = paymentService.add(paymentDTO);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public HttpEntity<?> ReadAll(){
        List<Payment> all = paymentRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> ReadOne(@PathVariable Integer id){
        Optional<Payment> byId = paymentRepository.findById(id);
        return ResponseEntity.ok().body(byId);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> Update(@PathVariable Integer id,@RequestBody PaymentDTO paymentDTO){
        ApiResponse update = paymentService.update(id, paymentDTO);
        return ResponseEntity.ok().body(update);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id){
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isPresent()) {
            Payment payment = byId.get();
            payment.setActive(false);
            paymentRepository.save(payment);
        } else return ResponseEntity.status(404).body("No such Payment");
        return ResponseEntity.ok().body("Deleted!");
    }
    @GetMapping("/details")
    public HttpEntity<?> getPaymentDetails(@RequestParam Integer id){
        ApiResponse paymentDetails = paymentService.getPaymentDetails(id);
        return ResponseEntity.ok().body(paymentDetails.getObject());
    }
}
