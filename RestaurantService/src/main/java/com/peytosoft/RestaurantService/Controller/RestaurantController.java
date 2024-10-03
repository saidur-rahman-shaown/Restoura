package com.peytosoft.RestaurantService.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.peytosoft.RestaurantService.Model.Restaurant;
import com.peytosoft.RestaurantService.Model.RestaurantDto;
import com.peytosoft.RestaurantService.Service.RestaurantService;



@RestController
@RequestMapping("restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("allRestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }
	
	@PostMapping("/addRestaurant/{ownerId}")
	public ResponseEntity<Integer> addRestaurant (@RequestBody RestaurantDto restaurantDto, @PathVariable Integer ownerId){
		return restaurantService.addRestaurant(restaurantDto,ownerId);
	}
	
	@GetMapping("/checkStatus/{restaurantId}")
    public ResponseEntity<String> checkStatus(@PathVariable Integer restaurantId){
        return restaurantService.checkStatus(restaurantId);
    }
	
	@PutMapping("/updateCuisineId/{restaurantId}")
    public ResponseEntity<String> updateCuisineId (@PathVariable Integer restaurantId, @RequestBody List<Integer> cuisineIds ){
		return restaurantService.updateCuisineId(restaurantId, cuisineIds);
	}
	
	@PostMapping("/uploadImage/{restaurantId}")
	public ResponseEntity<String> uploadImageToFIleSystem(@PathVariable Integer restaurantId, @RequestParam("image")MultipartFile file) throws IOException {
		return restaurantService.uploadImageToFileSystem(restaurantId,file);
		
	}
	
	@GetMapping("/downloadImage/{restaurantId}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Integer restaurantId) throws IOException {
		byte[] imageData=restaurantService.downloadImageFromFileSystem(restaurantId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
	
	@GetMapping("/getRestaurant")
    public ResponseEntity<Optional<Restaurant>> getRestaurant(@RequestParam Integer restaurantId){
        return restaurantService.getRestaurant(restaurantId);
    }
	
	

}
