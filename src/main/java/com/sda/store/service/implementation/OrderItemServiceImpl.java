package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.sda.store.repository.OrderItemRepository;
import com.sda.store.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
}
