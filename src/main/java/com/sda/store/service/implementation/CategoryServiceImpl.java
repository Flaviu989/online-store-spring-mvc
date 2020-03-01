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
	public String findNameOfCategoryById(int id) {
		return categoryRepository.findByIdCategory(id).getName();
	}

	@Override
	public Category findCategoryById(int id) {
		return categoryRepository.findByIdCategory(id);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findSubCategory() {
		return categoryRepository.findBysubCategoriesIsEmptyOrderByNameAscIdCategoryAsc();
	}

	@Override
	public List<Category> possibleSuperCategories() {
		return categoryRepository.findPossibleSuperCategories();
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteProductWithId(int id) {
		categoryRepository.deleteById(id);
	}
}
