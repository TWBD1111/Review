package com.ispan.eeit69.service;

import java.util.List;
import java.util.Optional;

import com.ispan.eeit69.model.Category;

public interface CategoryService {
	void resetCategoryTable();
	
	void save(Category category);
	boolean existsByCategoryId(Category category);
	boolean isPersist(Category category);	
	void detach(Category category);
	
	void update(Category category);
	
	void deleteById(Integer id);
	
	List<Category> findAll();
	Optional<Category> findById(Integer id);	
	Category findByCategoryId(String categoryId);
	
	List<Category> getAllCategories();

	Category getCategoryById(Integer id);

	void saveCategory(Category Category);

	void deleteCategory(Integer id);
	
}
