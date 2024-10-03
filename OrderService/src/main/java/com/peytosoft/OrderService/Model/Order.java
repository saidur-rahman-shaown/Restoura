package com.peytosoft.OrderService.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orderplacement")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cuisineId; 
    private Integer customerId;  // ID of the customer
    private Integer restaurantId;  // ID of the restaurant
    private String restaurantName;  
    private String cuisineName; 
    private String cuisinePrice;
    private Integer quantity; 
    private String pickupTime;  // "Real time pickup (3-4 hours)" or "Another day pickup"
    private String status;  // Status of the order (e.g., "Pending", "Completed", etc.)

}
