package com.sda.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sda.store.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByIdCategory(int id);

//	find main categories
	List<Category> findBySuperCategoryNull();

//	find sub-categories
	List<Category> findBysubCategoriesIsEmptyOrderByNameAscIdCategoryAsc();

//	find main categories or first layer sub-categories
	List<Category> findByProductsNull();

	@Query("SELECT category FROM Category category WHERE category.products IS EMPTY")
	List<Category> findPossibleSuperCategories();

}
