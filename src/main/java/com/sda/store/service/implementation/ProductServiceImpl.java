package com.sda.store.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.Product;
import com.sda.store.repository.ProductRepository;
import com.sda.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findProductByCategory(int id) {
		return productRepository.findByCategoryIdCategory(id);
	}
}
