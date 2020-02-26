package com.sda.store.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.Order;
import com.sda.store.repository.OrderRepository;
import com.sda.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List findAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public List<Order> findOrdersFromUser(String username) {
		return orderRepository.findByUserUsername(username);
	}

	@Override
	public void deleteOrderWithId(int id) {
		orderRepository.deleteById(id);
	}

}
