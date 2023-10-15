package com.ispan.eeit69.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findByCategoryId(String categoryId);
	Optional<Category> findById(Integer id);
	
	Category findByCategoryName(String categoryName);
	
	@Override
	boolean existsById(Integer id) ;
}
