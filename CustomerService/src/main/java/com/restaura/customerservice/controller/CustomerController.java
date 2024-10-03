package com.restaura.customerservice.controller;

import com.restaura.customerservice.dto.CustomerDTO;
import com.restaura.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
        Optional<CustomerDTO> customerDTO = customerService.getCustomerByEmail(email);
        return customerDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @PutMapping("/{email}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String email, @RequestBody CustomerDTO customerDetails) {
        Optional<CustomerDTO> updatedCustomer = customerService.updateCustomer(email, customerDetails);
        return updatedCustomer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
        boolean deleted = customerService.deleteCustomer(email);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

