package com.sda.store.service;

import java.util.List;

import com.sda.store.model.Category;

public interface CategoryService {

	List<Category> findMainCategories();

	String findNameOfCategoryById(int id);

	Category findCategoryById(int id);

	List<Category> findAllCategories();

	List<Category> findSubCategory();

	List<Category> possibleSuperCategories();

	void save(Category category);

	void deleteProductWithId(int id);

}
