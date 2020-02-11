package com.sda.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda.store.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

//	find main categories
	List<Category> findBySuperCategoryNull();

}
