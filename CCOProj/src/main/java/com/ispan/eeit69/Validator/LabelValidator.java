package com.ispan.eeit69.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.service.LabelService;

@Component
public class LabelValidator implements Validator{
	
	LabelService labelService;

	public LabelValidator(LabelService labelService) {
		super();
		this.labelService = labelService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Label.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "labelId", "label.labelId.empty.error", "必須輸入標籤編號"); 
		ValidationUtils.rejectIfEmpty(errors, "labelName", "label.labelName.empty.error", "必須輸入標籤名稱");
		ValidationUtils.rejectIfEmpty(errors, "labelPrice", "label.labelName.empty.error", "必須輸入標籤價格");
	}
}
