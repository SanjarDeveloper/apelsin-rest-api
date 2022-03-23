package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.resInvoiceDTO;
import com.example.apelsinrestapi.DTO.resPaymentDTO;
import com.example.apelsinrestapi.DTO.resRES;
import com.example.apelsinrestapi.entity.Invoice;
import com.example.apelsinrestapi.entity.Payment;
import com.example.apelsinrestapi.repository.InvoiceRepository;
import com.example.apelsinrestapi.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OverpaidInvoicesService {
    final PaymentRepository paymentRepository;
    final InvoiceRepository invoiceRepository;

    public ApiResponse getOverpaidInvoices() {
//        List<Payment> paymentList = new ArrayList<>();
//        List<Invoice> allInvoice = invoiceRepository.findAll();
//        for (Invoice invoice : allInvoice) {
//            for (Payment payment : paymentRepository.findAllByInvoiceId(invoice.getId())) {
//                   paymentList.add(payment);
//            }
//        }
//        List<resPaymentDTO> list = paymentTOPaymentDTO(paymentList);
//        List<resPaymentDTO> Final = new ArrayList<>();
//        Double amount = list.get(0).getAmount();
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getInvoice_id().equals(list.get(i+1).getInvoice_id())){
//                amount = amount + list.get(i+1).getAmount();
//            }
//        }
//        List<Payment> all = new ArrayList<>();
//        Set<Integer> invoiceIDs = new HashSet<>();
//        for (Invoice invoice : invoiceRepository.findAll()) {
//            invoiceIDs.add(invoice.getId());
//        }
//        Iterator<Integer> iterator = invoiceIDs.iterator();
//        while (iterator.hasNext()) {
//            List<Payment> byInvoiceId = paymentRepository.findByInvoiceId(iterator.next());
//            for (Payment payment : byInvoiceId) {
//                all.add(payment);
//            }
//
//        }
//        List<resPaymentDTO> paymentDTOS = new ArrayList<>();
//        for (int c = 0; c < all.size()-1; c++) {
//            if (all.get(c).getInvoice().equals(all.get(c + 1).getInvoice())) {
//                paymentDTOS.add(new resPaymentDTO(all.get(c).getId(),all.get(c).getAmount()+all.get(c+1).getAmount(),all.get(c).getTime(),all.get(c).getInvoice().getId()));
//            }
//        }
        Set<Invoice> set = new HashSet<>(invoiceRepository.findAll());
        List<resRES> list = new ArrayList<>();
        Iterator<Invoice> iterator = set.iterator();
        Double amount = 0.00;
        Integer invoiceid = null;
        while (iterator.hasNext()) {
            List<Payment> byInvoiceId = paymentRepository.findByInvoiceId(iterator.next().getId());
            if (!byInvoiceId.isEmpty()) {
                for (Payment payment : byInvoiceId) {
                    amount = amount + payment.getAmount();
                    invoiceid = payment.getInvoice().getId();
                }
                list.add(new resRES(invoiceid, amount));
                amount = 0.00;
                invoiceid = null;
            }
        }
        Double amount1 = 0.00;
        Integer invoice_id = 0;
        Iterator<Invoice> iterator1 = set.iterator();
        List<resRES> resList = new ArrayList<>();
        while (iterator1.hasNext()) {
            Optional<Invoice> byId = invoiceRepository.findById(iterator1.next().getId());
            if (byId.isPresent()) {
                for (resRES resRES : list) {
                    if (byId.get().getId().equals(resRES.getInvoice_id())){
                        amount1 = byId.get().getAmount() - resRES.getAmount();
                        invoice_id = resRES.getInvoice_id();
                    }
                }
                resList.add(new resRES(invoice_id,amount1));
            }
            amount1 = 0.00;
            invoice_id = 0;
        }

        for (int i = 0; i < resList.size(); i++) {
            if (resList.get(i).getAmount().equals(0.00) || resList.get(i).getInvoice_id().equals(0)){
                resList.remove(i);
            }
            else if(resList.get(i).getInvoice_id().equals(0) && resList.get(i).getAmount().equals(0.00)){
                resList.remove(i);
            }
        }

        return new ApiResponse(nullremover(resList));
    }

    public static List<resRES> nullremover(List<resRES> resList) {
        for (resRES resRES : resList) {
            if (resRES.getAmount().equals(0.00) || resRES.getInvoice_id().equals(0)){
                resList.remove(resRES);
            }
            else if (resRES.getInvoice_id().equals(0) && resRES.getAmount().equals(0.00)){
                resList.remove(resRES);
            }
        }

        return resList;
    }
}
