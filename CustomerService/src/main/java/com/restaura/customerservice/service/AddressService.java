package com.restaura.customerservice.service;

import com.restaura.customerservice.entities.Address;
import com.restaura.customerservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> updateAddress(Long id, Address addressDetails) {
        return addressRepository.findById(id).map(address -> {
            address.setAddressLine1(addressDetails.getAddressLine1());
            address.setAddressLine2(addressDetails.getAddressLine2());
            address.setCity(addressDetails.getCity());
            address.setState(addressDetails.getState());
            address.setPostalCode(addressDetails.getPostalCode());
            address.setCountry(addressDetails.getCountry());
            return addressRepository.save(address);
        });
    }

    public boolean deleteAddress(Long id) {
        return addressRepository.findById(id).map(address -> {
            addressRepository.delete(address);
            return true;
        }).orElse(false);
    }
}
