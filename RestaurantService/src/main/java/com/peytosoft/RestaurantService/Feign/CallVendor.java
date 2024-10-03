package com.peytosoft.RestaurantService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("VENDORSERVICE")
public interface CallVendor {

	@PostMapping("vendor/updateRestaurantId")
    public ResponseEntity<String> updateRestaurantId (@RequestParam Integer restaurantId,@RequestParam Integer userId);

}
