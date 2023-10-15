package com.ispan.eeit69.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ispan.eeit69.model.Product;
import com.ispan.eeit69.service.ProductService;

@Component
public class ProductValidator implements Validator{
	
	ProductService productService;

	public ProductValidator(ProductService productService) {
		super();
		this.productService = productService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "productId", "product.productId.empty.error", "必須輸入商品編號"); 
		ValidationUtils.rejectIfEmpty(errors, "productName", "product.productName.empty.error", "必須輸入商品名稱");
		ValidationUtils.rejectIfEmpty(errors, "category", "product.category.empty.error", "必須選擇類別");
		ValidationUtils.rejectIfEmpty(errors, "productDescription", "product.productDescription.empty.error", "必須輸入商品介紹");
		ValidationUtils.rejectIfEmpty(errors, "productPortion", "product.productPortion.empty.error", "必須輸入商品份量");
		ValidationUtils.rejectIfEmpty(errors, "productPrice", "product.productPrice.empty.error", "必須輸入商品價格");
		ValidationUtils.rejectIfEmpty(errors, "productStock", "product.productStock.empty.error", "必須輸入商品庫存");
//		ValidationUtils.rejectIfEmpty(errors, "created_at", "product.created_at.empty.error", "必須輸入建立時間");
		ValidationUtils.rejectIfEmpty(errors, "labels", "product.labels.empty.error", "必須選擇客製標籤");
		
		Product product = (Product)target;
	
		if (product.getImage() == null || product.getImage().length() == 0) {
			if (product.getFileName().length() == 0) {
				errors.rejectValue("fileName", "product.image.empty.error", "必須挑選商品照片");				
			}
		}
	}
}
