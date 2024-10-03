package com.restaura.customerservice.service;

import com.restaura.customerservice.dto.CustomerDTO;
import com.restaura.customerservice.entities.Address;
import com.restaura.customerservice.entities.Customer;
import com.restaura.customerservice.repository.CustomerRepository;
import com.restaura.customerservice.util.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> getCustomerByEmail(String email) {
        return customerRepository.findById(email)
                .map(CustomerMapper::toCustomerDTO);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomerEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toCustomerDTO(savedCustomer);
    }

    public Optional<CustomerDTO> updateCustomer(String email, CustomerDTO customerDetails) {
        return customerRepository.findById(email).map(customer -> {
            customer.setName(customerDetails.getName());
            customer.setAge(customerDetails.getAge());
            customer.setOccupation(customerDetails.getOccupation());
            customer.setSex(customerDetails.getSex());
            customer.setContactNo(customerDetails.getContactNo());

            // Map AddressDTO to Address entities and set the customer
            List<Address> addresses = customerDetails.getAddresses().stream()
                    .map(addressDTO -> {
                        Address address = CustomerMapper.toAddressEntity(addressDTO);
                        address.setCustomer(customer);
                        return address;
                    }).collect(Collectors.toList());

            customer.setAddresses(addresses);

            Customer updatedCustomer = customerRepository.save(customer);
            return CustomerMapper.toCustomerDTO(updatedCustomer);
        });
    }

    public boolean deleteCustomer(String email) {
        return customerRepository.findById(email).map(customer -> {
            customerRepository.delete(customer);
            return true;
        }).orElse(false);
    }

    public CustomerDTO TestcreateCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomerEntity(customerDTO);
//        System.out.println(customer);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toCustomerDTO(savedCustomer);
//        return CustomerMapper.toCustomerDTO(customer);
    }
}
