package com.ispan.eeit69.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.service.CategoryService;

@Component
public class CategoryValidator implements Validator{
	CategoryService categoryService;

	public CategoryValidator(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "categoryId", "category.categoryId.empty.error", "必須輸入類別編號"); 
		ValidationUtils.rejectIfEmpty(errors, "categoryName", "category.categoryName.empty.error", "必須輸入類別名稱");				
	}	
}
