package com.peytosoft.RestaurantService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
	
	private String name;
	private String ownerName;
	private String cuisineType;
	private String description;
	private String operatingHours;
	private Boolean partyOrderAvailable;
	private Boolean offHourDeliveryAvailable;
	private Boolean openOrClosed;
	private String email;
	private String contactNo;

}
