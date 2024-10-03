package com.peytosoft.VendorService.Model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String ssn;
	private Integer age;
	private String sex;
	private Long contactNo;
	private String streetNo;
	private String streetNo2;
	private String city;
	private String postalCode;
	private String province;
	private String  country;
	
	

}
