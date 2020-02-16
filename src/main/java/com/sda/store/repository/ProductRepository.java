package com.sda.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByIdProduct(int id);

	List<Product> findByCategoryIdCategory(int id);

}
