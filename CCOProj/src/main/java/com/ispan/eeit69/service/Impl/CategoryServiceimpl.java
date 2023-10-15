package com.ispan.eeit69.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.repository.CategoryRepository;
import com.ispan.eeit69.repository.CategoryRepositoryCustom;
import com.ispan.eeit69.service.CategoryService;

@Service
@Transactional
public class CategoryServiceimpl implements CategoryService {
	
	CategoryRepository categoryRepository;
	CategoryRepositoryCustom categoryRepositoryCustom;
	
	public CategoryServiceimpl(CategoryRepository categoryRepository,
			CategoryRepositoryCustom categoryRepositoryCustom) {
		super();
		this.categoryRepository = categoryRepository;
		this.categoryRepositoryCustom = categoryRepositoryCustom;
	}

	@Override
	public void resetCategoryTable() {
		throw new RuntimeException("本系統未提供此功能");
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void update(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Category findByCategoryId(String categoryId) {
		Category emp = categoryRepository.findByCategoryId(categoryId);
		return emp;
	}

	@Override
	public boolean existsByCategoryId(Category category) {
		if (categoryRepositoryCustom.isPersist(category)) {
			categoryRepositoryCustom.detach(category);
		}
		Category emp = findByCategoryId( category.getCategoryId() );
		return  emp != null;
	}

	@Override
	public boolean isPersist(Category category) {
		boolean ans = categoryRepository.existsById(category.getId());
		return ans;
	}

	@Override
	public void detach(Category category) {
		categoryRepositoryCustom.detach(category);		
	}
	
	@Override
	public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

	@Override
	public Category getCategoryById(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public void saveCategory(Category Category) {
		categoryRepository.save(Category);
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
	}
	
}
