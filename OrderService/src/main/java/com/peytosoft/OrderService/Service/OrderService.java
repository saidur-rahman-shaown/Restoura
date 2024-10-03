package com.peytosoft.OrderService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peytosoft.OrderService.Dao.OrderServiceDao;
import com.peytosoft.OrderService.Model.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderServiceDao orderServiceDao;

	public Order createOrder(Order orderPlacement) {
		// TODO Auto-generated method stub
		return orderServiceDao.save(orderPlacement);
	}

	public Optional<Order> getOrderById(Integer id) {
		// TODO Auto-generated method stub
		return orderServiceDao.findById(id);
	}

	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderServiceDao.findAll();
	}

	public Order updateOrder(Integer id, Order orderPlacement) {
		if (orderServiceDao.existsById(id)) {
            orderPlacement.setId(id);
            return orderServiceDao.save(orderPlacement);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
	}

	public void deleteOrder(Integer id) {
		// TODO Auto-generated method stub
		orderServiceDao.deleteById(id);
	}

}
