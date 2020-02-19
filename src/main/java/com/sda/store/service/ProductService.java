package com.sda.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sda.store.model.Product;

public interface ProductService {

	Product findProductByIdProduct(int id);

	public Page<Product> findAllProducts(Pageable pageable);

	public Page<Product> findProductByCategoryId(Pageable pageable, int id);

	List<Product> findAllProductsByNmae(String name);

}
