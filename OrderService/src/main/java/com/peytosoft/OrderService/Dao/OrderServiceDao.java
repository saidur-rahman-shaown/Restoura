package com.peytosoft.OrderService.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peytosoft.OrderService.Model.Order;

public interface OrderServiceDao extends JpaRepository<Order, Integer> {

}
