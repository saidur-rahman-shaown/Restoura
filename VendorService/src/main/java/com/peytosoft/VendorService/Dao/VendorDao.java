package com.peytosoft.VendorService.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peytosoft.VendorService.Model.Vendor;

@Repository
public interface VendorDao extends JpaRepository<Vendor,Integer>{

	Optional<Vendor> findByUserId(Integer userId);

}
