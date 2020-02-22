package com.sda.store.service;

import java.util.List;

import com.sda.store.model.Category;

public interface CategoryService {

	List<Category> findMainCategories();

	String findCategoryById(int id);

	List<Category> findAllCategories();

	List<Category> findSubCategory();

}
