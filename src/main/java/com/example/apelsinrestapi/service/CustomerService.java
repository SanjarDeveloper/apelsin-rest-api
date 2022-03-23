package com.example.apelsinrestapi.service;

import com.example.apelsinrestapi.DTO.ApiResponse;
import com.example.apelsinrestapi.DTO.CustomerDTO;
import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

   final CustomerRepository customerRepository;

   public ApiResponse add(CustomerDTO customerDTO) {
      Customer customer = new Customer();
      customer.setAddress(customerDTO.getAddress());
      customer.setCountry(customerDTO.getCountry());
      customer.setName(customerDTO.getName());
      customer.setPhone(customerDTO.getPhone());
      customer.setActive(customerDTO.isActive());
      customerRepository.save(customer);
      return new ApiResponse("Added!",true);
   }
   public ApiResponse update(Integer id,CustomerDTO customerDTO){
      Optional<Customer> byId = customerRepository.findById(id);
      if (byId.isEmpty()) return new ApiResponse("No such id!",false);
      else {
         Customer customer = byId.get();
         customer.setName(customerDTO.getName());
         customer.setCountry(customerDTO.getCountry());
         customer.setPhone(customerDTO.getPhone());
         customer.setAddress(customerDTO.getAddress());
         customer.setActive(customerDTO.isActive());
         customerRepository.save(customer);
      }
      return new ApiResponse("Updated!",true);
   }
}
