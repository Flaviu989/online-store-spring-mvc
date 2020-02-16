package com.sda.store.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.Category;
import com.sda.store.repository.CategoryRepository;
import com.sda.store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findMainCategories() {
		return categoryRepository.findBySuperCategoryNull();
	}

	@Override
	public String findCategoryById(int id) {
		return categoryRepository.findByIdCategory(id).getName();
	}
}
