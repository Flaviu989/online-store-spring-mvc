package com.sda.store.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Product> findAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> findProductByCategoryId(Pageable pageable, int id) {
		return productRepository.findByCategoryIdCategory(id, pageable);
	}

	@Override
	public List<Product> findAllProductsByNmae(String name) {
		return productRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(name, name);
	}

	@Override
	public List findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProductWithId(int id) {
		productRepository.deleteById(id);
	}

}
