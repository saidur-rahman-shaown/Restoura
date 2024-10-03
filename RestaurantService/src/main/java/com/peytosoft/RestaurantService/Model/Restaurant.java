package com.peytosoft.RestaurantService.Model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="restaurant")
@Builder
public class Restaurant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private Integer ownerId;
	private String ownerName;
	private String cuisineType;
	private String description;
	private String status;
	private String operatingHours;
	private Boolean partyOrderAvailable;
	private Boolean offHourDeliveryAvailable;
	private Boolean openOrClosed;
	private String email;
	private String contactNo;
	private String imageFilePath;
	
	@ElementCollection
	private List<Integer> cuisineIds;
	
	
	

}
