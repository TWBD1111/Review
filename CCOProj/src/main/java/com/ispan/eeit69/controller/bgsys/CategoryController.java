package com.ispan.eeit69.controller.bgsys;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.Validator.CategoryValidator;
import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.service.CategoryService;

@Controller
public class CategoryController extends AbstractController{
	Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	CategoryService categoryService;
	CategoryValidator categoryValidator;
	
    public CategoryController(CategoryService categoryService, CategoryValidator categoryValidator) {
		super();
		this.categoryService = categoryService;
		this.categoryValidator = categoryValidator;
	}

	@GetMapping("/category")
	public String findAll(Model model) {
		List<Category>  Categories = categoryService.findAll();
		model.addAttribute(Categories);   // 使用預設的識別字串 "employeeList"
		return "Category_all";
	}
	
	@GetMapping("/category/insertCategory")
	public String sendEmptyData(Model model) {
		Category category = new Category();
		model.addAttribute(category);
		return "Category_insert";
	}
	
	@PostMapping("/category/insertCategory")
	public String saveCategory(@ModelAttribute Category category, BindingResult result, 
			Model model, RedirectAttributes ra) throws SerialException, SQLException {

		categoryValidator.validate(category, result);
		if (result.hasErrors()) {
			log.warn("/categorysdjpa, 前端使用者送回的表單含有錯誤資料");
			log.warn("/categorysdjpa, -------------------------------------");
			// -------------------------------------
			// 下列四列敘述只有需要獲知由於格式錯而無法綁定時所需的錯誤訊息的Key才會用到它們
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				log.warn("/categorysdjpa, " + error.toString());
			}
			log.warn("/categorysdjpa, -------------------------------------");
			return "Category_insert";
		}
		if (categoryService.existsByCategoryId(category)) {
			log.warn("/categorysdjpa, 提供的類別編號已存在: " + category.getCategoryId());
			result.rejectValue("categoryId", "category.categoryId.exist.error", "類別編號已存在，請更換新的類別編號");
			return "Category_insert";
		}
		categoryService.save(category);
		
		ra.addFlashAttribute("message", "<font color='blue'>資料新增成功</font>");
		return "redirect:/category";
	}

	@DeleteMapping("/category/CategoryDelete/{id}")
	public String deleteCategory(RedirectAttributes ra, 
			@PathVariable Integer id,
			@RequestParam String empNo
			) {
		try {
			categoryService.deleteById(id);
			ra.addFlashAttribute("message", "<font color='green'>已刪除類別編號: " +  empNo + " 之紀錄</font>");
			log.info("/productsdjpa, 已刪除類別編號: " +  empNo + " 之紀錄");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "<font color='red'>刪除類別編號: " +  empNo + " 之紀錄失敗:" + e.getMessage() + "</font>");
			log.error("/productsdjpa, 刪除類別編號: " +  empNo + " 之紀錄失敗");
			e.printStackTrace();
		}
		return "redirect:/category";
	}
	
}
