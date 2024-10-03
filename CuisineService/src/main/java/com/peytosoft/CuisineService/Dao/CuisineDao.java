package com.peytosoft.CuisineService.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peytosoft.CuisineService.Model.Cuisine;

@Repository
public interface CuisineDao extends JpaRepository<Cuisine,Integer>{

	List<Cuisine> findByRestaurantId(Integer restaurantId );

}
