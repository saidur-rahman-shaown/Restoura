package com.peytosoft.VendorService.Model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vendor")
@Builder
public class Vendor {
	
	@Id
	private Integer userId;
	
	private String firstName;
	private String lastName;
	private String email;
	private String imageFilePath;
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
	
	@ElementCollection
	private List <Integer> restaurantIds;
		

}
