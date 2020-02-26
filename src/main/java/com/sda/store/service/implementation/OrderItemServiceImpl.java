package com.sda.store.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.OrderItem;
import com.sda.store.repository.OrderItemRepository;
import com.sda.store.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void saveOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}

	@Override
	public List<OrderItem> findProductsInCart(String username) {
		return orderItemRepository.findByOrderNullAndUserUsername(username);
	}

	@Override
	public OrderItem findOrderItemWithId(int id) {
		return orderItemRepository.findByIdOrderItem(id);
	}

	@Override
	public void saveOrderItemWithId(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}

	@Override
	public void saveOrderItemList(List<OrderItem> orderItemes) {
		orderItemRepository.saveAll(orderItemes);
	}

	@Override
	public void deleteOrderItemWithId(int id) {
		orderItemRepository.deleteById(id);
	}

}
