package com.peytosoft.CuisineService.Service;

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

import com.peytosoft.CuisineService.Dao.CuisineDao;
import com.peytosoft.CuisineService.Feign.CallRestaurant;
import com.peytosoft.CuisineService.Model.Cuisine;
import com.peytosoft.CuisineService.Model.CuisineDto;




@Service
public class CuisineService {
	
	@Autowired
	private CuisineDao cuisineDao;
	
	@Autowired
	private CallRestaurant callRestaurant;
	
	private Integer cuisineId;
	
	private final String FOLDER_PATH="C:\\Users\\Nazmul\\Desktop\\cuisines\\";

	public ResponseEntity<Integer> addCuisine(CuisineDto cuisineDto, Integer restaurantId) {
		
		//First checking status of the restaurant
		//String status = callRestaurant.checkStatus(restaurantId).getBody();
			
			cuisineDao.save(Cuisine.builder()
					   .restaurantId(restaurantId)
			           .cuisineName(cuisineDto.getCuisineName())
			           .category(cuisineDto.getCategory())
			           .description(cuisineDto.getDescription())
			           .price(cuisineDto.getPrice())
			           .availability(cuisineDto.getAvailability())
			           .build());
			
			// now, updating the cuisingIds in the restaurant database
			List<Cuisine> cuisineList = cuisineDao.findByRestaurantId(restaurantId);
			
			for (int i = 0; i < cuisineList.size(); i++) {

	            if(cuisineList.get(i).getCuisineName()== cuisineDto.getCuisineName())
	            	
	            	cuisineId = cuisineList.get(i).getId();
	        
			}
		
			
			List<Integer>cuisineIds = new ArrayList<>();
			
			for (int i = 0; i < cuisineList.size(); i++) {

	            // Using get() method to access particular element
	            cuisineIds.add(cuisineList.get(i).getId());
	        }
			
			callRestaurant.updateCuisineId(restaurantId, cuisineIds);

			
			return new ResponseEntity<>(cuisineId ,HttpStatus.CREATED);
		

	}

	public ResponseEntity<List<Cuisine>> getAllCuisines(Integer restaurantId) {
		try {
            return new ResponseEntity<>(cuisineDao.findByRestaurantId(restaurantId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new 
        		ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> uploadImageToFileSystem(Integer cuisineId, MultipartFile file) {
        
		String originalFileName= file.getOriginalFilename();
		
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = cuisineId + fileExtension;
				
		String filePath=FOLDER_PATH+newFileName;
		 
		 
				Cuisine existingCuisine = cuisineDao.findById(cuisineId).orElse(null);
				existingCuisine.setImageFilePath(filePath);
				cuisineDao.save(existingCuisine);
		

	       try {
				file.transferTo(new File(filePath));
			} 
		     catch (IOException e) {
				
		    	 return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
			}
  
	       return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	
	public byte[] downloadImageFromFileSystem(Integer cuisineId) {
		 Optional<Cuisine> cuisine = cuisineDao.findById(cuisineId);
	        String filePath=cuisine.get().getImageFilePath();
	       
			try {
				byte[]	images = Files.readAllBytes(new File(filePath).toPath());
				return images;
				
			} catch (IOException e) {
				return null;
			}
	       
	}

}
