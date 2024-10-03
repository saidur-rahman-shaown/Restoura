package com.peytosoft.OrderService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peytosoft.OrderService.Model.Order;
import com.peytosoft.OrderService.Service.OrderService;



@RestController
@RequestMapping("order/")
public class OrderServiceController {
	
	@Autowired
    private OrderService orderPlacementService;

	
	@PostMapping("createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order orderPlacement) {
        Order createdOrder = orderPlacementService.createOrder(orderPlacement);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("getOrderById/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> orderPlacement = orderPlacementService.getOrderById(id);
        return orderPlacement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderPlacementService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("changeStatus/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order orderPlacement) {
        try {
            Order updatedOrder = orderPlacementService.updateOrder(id, orderPlacement);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleteOrder/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderPlacementService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
