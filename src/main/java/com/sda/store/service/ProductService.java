package com.sda.store.service;

import java.util.List;

import com.sda.store.model.Product;

public interface ProductService {

	Product findProductByIdProduct(int id);
	List<Product> findProductByCategoryId(int id);

}
