package com.peytosoft.RestaurantService.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.peytosoft.RestaurantService.Dao.RestaurantDao;
import com.peytosoft.RestaurantService.Feign.CallVendor;
import com.peytosoft.RestaurantService.Model.Restaurant;
import com.peytosoft.RestaurantService.Model.RestaurantDto;




@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private CallVendor callVendor;
	
	private final String FOLDER_PATH="C:\\Users\\Nazmul\\Desktop\\restaurants\\";

	public ResponseEntity<Integer> addRestaurant(RestaurantDto restaurantDto, Integer ownerId) {
	Restaurant restaurant	= restaurantDao.save(Restaurant.builder()
				           .name(restaurantDto.getName())
				           .ownerId(ownerId)
				           .ownerName(restaurantDto.getOwnerName())
				           .cuisineType(restaurantDto.getCuisineType())
				           .description(restaurantDto.getDescription())
				           .status("Approved")
				           .operatingHours(restaurantDto.getOperatingHours())
				           .partyOrderAvailable(restaurantDto.getPartyOrderAvailable())
				           .offHourDeliveryAvailable(restaurantDto.getOffHourDeliveryAvailable())
				           .openOrClosed(restaurantDto.getOpenOrClosed())
				           .email(restaurantDto.getEmail())
				           .contactNo(restaurantDto.getEmail())
				           .build());
		
		
		
		// connect with vendor service and update restaurantId
		callVendor.updateRestaurantId(restaurant.getId(), ownerId);
		
		
		//send notification to admin service for Approval
		// will not be available to public and can't create cuisine if the status is changed to "approved"
		return new ResponseEntity<>(restaurant.getId() ,HttpStatus.CREATED);
	}

	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		try {
            return new ResponseEntity<>(restaurantDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String>checkStatus(Integer restaurantId) {
		
		Optional<Restaurant> res = restaurantDao.findById(restaurantId);
		
		return new ResponseEntity<>(res.get().getStatus(),HttpStatus.OK);
	}

	
	public ResponseEntity<String> updateCuisineId(Integer restaurantId, List<Integer> cuisineIds) {
		
       Restaurant existingRestaurant = restaurantDao.findById(restaurantId).orElse(null);
		
		existingRestaurant.setCuisineIds(cuisineIds);
		
		restaurantDao.save(existingRestaurant);
		
		return new ResponseEntity<>("Restaurant updated successfully",HttpStatus.OK);
		}

	public ResponseEntity<String> uploadImageToFileSystem(Integer restaurantId, MultipartFile file) {
        
		String originalFileName= file.getOriginalFilename();
		
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = restaurantId + fileExtension;
				
		String filePath=FOLDER_PATH+newFileName;
		 
				Restaurant existingRestaurant = restaurantDao.findById(restaurantId).orElse(null);
				existingRestaurant.setImageFilePath(filePath);
				restaurantDao.save(existingRestaurant);
		

	       try {
				file.transferTo(new File(filePath));
			} 
		     catch (IOException e) {
				
		    	 return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
			}
  
	       return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public byte[] downloadImageFromFileSystem(Integer restaurantId) {
		Optional<Restaurant> restaurant = restaurantDao.findById(restaurantId);
        String filePath=restaurant.get().getImageFilePath();
       
		try {
			byte[]	images = Files.readAllBytes(new File(filePath).toPath());
			return images;
			
		} catch (IOException e) {
			return null;
		}
	}

	public ResponseEntity<Optional<Restaurant>> getRestaurant(Integer restaurantId) {
		try {
            return new ResponseEntity<>(restaurantDao.findById(restaurantId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	
	

}
