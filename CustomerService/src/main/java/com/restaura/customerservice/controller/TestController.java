package com.restaura.customerservice.controller;

import com.restaura.customerservice.dto.CustomerDTO;
import com.restaura.customerservice.entities.Address;
import com.restaura.customerservice.entities.Customer;
import com.restaura.customerservice.repository.CustomerRepository;
import com.restaura.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("createcustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return ResponseEntity.ok().body(customer);
    }
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.TestcreateCustomer(customerDTO);


    }

    @GetMapping("")
    public ResponseEntity<Customer> test(){

        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");
        customer.setAge(30);
        customer.setOccupation("Software Engineer");
        customer.setSex("Male");
        customer.setContactNo("+1234567890");

        // Step 2: Create Address instances and associate them with the customer
        Address address1 = new Address();
        address1.setAddressLine1("123 Main St");
        address1.setAddressLine2("Apt 4B");
        address1.setCity("New York");
        address1.setState("NY");
        address1.setPostalCode("10001");
        address1.setCountry("USA");
        address1.setCustomer(customer);  // Link this address to the customer

        Address address2 = new Address();
        address2.setAddressLine1("456 Elm St");
        address2.setAddressLine2("");
        address2.setCity("Los Angeles");
        address2.setState("CA");
        address2.setPostalCode("90001");
        address2.setCountry("USA");
        address2.setCustomer(customer);  // Link this address to the customer

        // Step 3: Create a list of addresses and add the address instances
        List<Address> addresses = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);

        // Assign the list of addresses to the customer
        customer.setAddresses(addresses);
         Customer save = customerRepository.save(customer);


        return ResponseEntity.ok().body(save);

    }
}
