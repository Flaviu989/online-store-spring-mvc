package com.sda.store.service;

import java.util.List;

import com.sda.store.model.Order;

public interface OrderService {

	List findAllOrders();

	void saveOrder(Order order);

	List<Order> findOrdersFromUser(String username);

	void deleteOrderWithId(int id);

}
