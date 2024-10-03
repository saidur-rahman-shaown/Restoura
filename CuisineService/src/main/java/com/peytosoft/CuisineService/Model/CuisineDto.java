package com.peytosoft.CuisineService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuisineDto {
	
	private String cuisineName;
	private String category;
	private String description;
	private String price;
	private String availability;

}
