package com.peytosoft.VendorService.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.peytosoft.VendorService.Dao.VendorDao;
import com.peytosoft.VendorService.Model.Vendor;
import com.peytosoft.VendorService.Model.VendorDto;

@Service
public class VendorService {
	
	@Autowired
	VendorDao vendorDao;

	@Value("${path.vendor.profile.picture}")
	String FOLDER_PATH ;

	public ResponseEntity<List<Vendor>> getAllVendors() {
		
		try {
            return new ResponseEntity<>(vendorDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        
	}

	public ResponseEntity<String> addVendor(VendorDto vendorDto, Integer userId) {
		
		vendorDao.save(Vendor.builder()
				   .userId(userId)
		           .firstName(vendorDto.getFirstName())
		           .lastName(vendorDto.getLastName())
		           .email(vendorDto.getEmail())
		           .ssn(vendorDto.getSsn())
		           .age(vendorDto.getAge())
		           .sex(vendorDto.getSex())
		           .contactNo(vendorDto.getContactNo())
		           .streetNo(vendorDto.getStreetNo())
		           .streetNo2(vendorDto.getStreetNo2())
		           .city(vendorDto.getCity())
		           .postalCode(vendorDto.getPostalCode())
		           .province(vendorDto.getProvince())
		           .country(vendorDto.getCountry())
		           .build());
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<String> uploadImageToFileSystem(Integer userId,MultipartFile file) {
		
		String originalFileName= file.getOriginalFilename();
		
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = userId + fileExtension;
				
		String filePath=FOLDER_PATH+newFileName;
		 
		 
				Vendor existingVendor = vendorDao.findByUserId(userId).orElse(null);
				existingVendor.setImageFilePath(filePath);
				vendorDao.save(existingVendor);
		

	       try {
				file.transferTo(new File(filePath));
			} 
		     catch (IOException e) {
				
		    	 return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
			}
  
	       return new ResponseEntity<>("Success",HttpStatus.CREATED);
	        
	}

	public byte[] downloadImageFromFileSystem(Integer userId) {
		 Optional<Vendor> vendor = vendorDao.findByUserId(userId);
	        String filePath=vendor.get().getImageFilePath();
	       
			try {
				byte[]	images = Files.readAllBytes(new File(filePath).toPath());
				return images;
				
			} catch (IOException e) {
				return null;
			}
	       
	}

	public ResponseEntity<String> updateRestaurantId(Integer restaurantId, Integer userId) {
		Vendor existingVendor = vendorDao.findByUserId(userId).orElse(null);
		
		
		List<Integer> restaurantIds = existingVendor.getRestaurantIds();
		
		if (restaurantIds == null) {
		    restaurantIds = new ArrayList<>();
		}
		restaurantIds.add(restaurantId);
		existingVendor.setRestaurantIds(restaurantIds);
		vendorDao.save(existingVendor);
		
		return new ResponseEntity<>("Vendor updated successfully",HttpStatus.OK);
	}

	public ResponseEntity<Optional<Vendor>> getVendorByUserId(Integer userId) {
		try {
            return new ResponseEntity<>(vendorDao.findByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
