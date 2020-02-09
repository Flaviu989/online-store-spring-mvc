package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.sda.store.repository.ProductRepository;
import com.sda.store.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
}
