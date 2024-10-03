package com.peytosoft.CuisineService.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.peytosoft.CuisineService.Model.Cuisine;
import com.peytosoft.CuisineService.Model.CuisineDto;
import com.peytosoft.CuisineService.Service.CuisineService;


@RestController
@RequestMapping("cuisine")
public class CuisineController {
	
	@Autowired
	CuisineService cuisineService;
	
	@PostMapping("addCuisine/{restaurantId}")
	public ResponseEntity<Integer> addCuisine (@RequestBody CuisineDto cuisineDto, @PathVariable Integer restaurantId){
		return cuisineService.addCuisine(cuisineDto,restaurantId);
	}
	
	@GetMapping("allCuisines/{restaurantId}")
    public ResponseEntity<List<Cuisine>> getAllCuisines(@PathVariable Integer restaurantId){
        return cuisineService.getAllCuisines(restaurantId);
    }
	
	@PostMapping("/uploadImage/{cuisineId}")
	public ResponseEntity<String> uploadImageToFIleSystem(@PathVariable Integer cuisineId, @RequestParam("image")MultipartFile file) throws IOException {
		return cuisineService.uploadImageToFileSystem(cuisineId,file);
		
	}
	
	@GetMapping("/downloadImage/{cuisineId}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Integer cuisineId) throws IOException {
		byte[] imageData=cuisineService.downloadImageFromFileSystem(cuisineId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

}
