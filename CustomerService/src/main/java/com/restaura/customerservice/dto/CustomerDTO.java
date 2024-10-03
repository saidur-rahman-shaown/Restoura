package com.restaura.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private String name;
    private String email;
    private List<AddressDTO> addresses;  // List of address DTOs to avoid recursion
    private Integer age;
    private String occupation;
    private String sex;
    private String contactNo;
}
