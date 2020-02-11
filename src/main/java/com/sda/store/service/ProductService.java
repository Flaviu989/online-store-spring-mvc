package com.sda.store.service;

import java.util.List;

import com.sda.store.model.Product;

public interface ProductService {

	List<Product> findProductByCategory(int id);

}
