package com.peytosoft.CuisineService.Feign;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("RESTAURANTSERVICE")
public interface CallRestaurant {
	
	@GetMapping("restaurant/checkStatus/{restaurantId}")
    public ResponseEntity<String> checkStatus(@PathVariable Integer restaurantId);
	
	@PutMapping("restaurant/updateCuisineId/{restaurantId}")
    public ResponseEntity<String> updateCuisineId (@PathVariable Integer restaurantId, @RequestBody List<Integer> cuisineIds );

}
