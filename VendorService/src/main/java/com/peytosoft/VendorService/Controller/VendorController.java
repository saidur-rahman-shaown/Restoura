package com.peytosoft.VendorService.Controller;


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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.peytosoft.VendorService.Model.Vendor;
import com.peytosoft.VendorService.Model.VendorDto;
import com.peytosoft.VendorService.Service.VendorService;

@RestController
@RequestMapping("vendor")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@GetMapping("/allVendors")
    public ResponseEntity<List<Vendor>> getAllVendors(){
        return vendorService.getAllVendors();
    }
	
	@GetMapping("/getVendor/{userId}")
    public ResponseEntity<Optional<Vendor>> getVendor(@PathVariable Integer userId){
        return vendorService.getVendorByUserId(userId);
    }
	
	@PostMapping("/addVendor/{userId}")
    public ResponseEntity<String> addVendor(@RequestBody VendorDto vendorDto, @PathVariable Integer userId){
       return vendorService.addVendor(vendorDto, userId);
    }
	
	@PostMapping("/uploadImage/{userId}")
	public ResponseEntity<String> uploadImageToFIleSystem(@PathVariable Integer userId, @RequestParam("image")MultipartFile file) throws IOException {
		return vendorService.uploadImageToFileSystem(userId,file);
		
	}
	
	@GetMapping("/downloadImage/{userId}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Integer userId) throws IOException {
		byte[] imageData=vendorService.downloadImageFromFileSystem(userId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
	
	@PostMapping("updateRestaurantId")
    public ResponseEntity<String> updateRestaurantId (@RequestParam Integer restaurantId,@RequestParam Integer userId){
		return vendorService.updateRestaurantId(restaurantId, userId);
	}

	

}
