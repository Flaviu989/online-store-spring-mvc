package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderService orderService;
}
