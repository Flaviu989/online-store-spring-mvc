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
	public Product findProductByIdProduct(int id) {
		return productRepository.findByIdProduct(id);
	}

	@Override
	public List<Product> findProductByCategoryId(int id) {
		return productRepository.findByCategoryIdCategory(id);
	}

}
