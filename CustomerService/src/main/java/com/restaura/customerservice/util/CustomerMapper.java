package com.restaura.customerservice.util;

import com.restaura.customerservice.dto.AddressDTO;
import com.restaura.customerservice.dto.CustomerDTO;
import com.restaura.customerservice.entities.Address;
import com.restaura.customerservice.entities.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDTO toCustomerDTO(Customer customer) {
        List<AddressDTO> addressDTOs = customer.getAddresses().stream()
                .map(CustomerMapper::toAddressDTO)
                .collect(Collectors.toList());

        return new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                addressDTOs,
                customer.getAge(),
                customer.getOccupation(),
                customer.getSex(),
                customer.getContactNo()
        );
    }

    public static AddressDTO toAddressDTO(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountry(),
                address.getCustomer().getEmail()  // Only include the customer's email
        );
    }

    public static Customer toCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());
        customer.setOccupation(customerDTO.getOccupation());
        customer.setSex(customerDTO.getSex());
        customer.setContactNo(customerDTO.getContactNo());

        // Map AddressDTO to Address entities and set the customer
        List<Address> addresses = customerDTO.getAddresses().stream()
                .map(CustomerMapper::toAddressEntity)
                .peek(address -> address.setCustomer(customer))
                .collect(Collectors.toList());

        customer.setAddresses(addresses);
        return customer;
    }

    public static Address toAddressEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setAddressLine1(addressDTO.getAddressLine1());
        address.setAddressLine2(addressDTO.getAddressLine2());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        return address;
    }
}

