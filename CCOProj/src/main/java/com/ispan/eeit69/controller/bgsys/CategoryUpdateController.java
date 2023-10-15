package com.ispan.eeit69.controller.bgsys;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.Validator.CategoryValidator;
import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.service.CategoryService;

@Controller
@SessionAttributes("previous_category_id") 
public class CategoryUpdateController extends AbstractController{
	Logger log = LoggerFactory.getLogger(CategoryUpdateController.class);
	
	CategoryService categoryService;
	// 檢查資料是否正確的驗證器(Insert+update)
	CategoryValidator categoryValidator;
	
	public CategoryUpdateController(CategoryService categoryService, CategoryValidator categoryValidator) {
		super();
		this.categoryService = categoryService;
		this.categoryValidator = categoryValidator;
	}
	@GetMapping("/category/findById/{id}")
	public String sendEmptyForm() {
		return "Category_update";
	}
	
	@PutMapping("/category/editCategory/{id}")
	public String updateCategory(@ModelAttribute Category category,
			BindingResult result,
			@RequestParam String previous_categoryId, 
			Model model, RedirectAttributes ra) throws SerialException, SQLException {
		// 對使用者輸入之資料進行驗證
		categoryValidator.validate(category, result);
		// 如果修改後的資料有錯誤
		if (result.hasErrors()) {    
			log.warn("/categorysdjpa, 前端使用者送回的表單含有錯誤資料");
			log.warn("/categorysdjpa, -------------------------------------");
			// -------------------------------------
			// 下列迴圈僅為了找出顯示我們需要之錯誤訊息所需的key: 
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				log.warn("/categorysdjpa, " + error.toString());
			}
			log.warn("/categorysdjpa, -------------------------------------");
			return "Category_update";
		}
		// 如果修改員工編號，需要檢查修改後的員工編號是否已經存在
		if  (!previous_categoryId.equals(category.getCategoryId()) ) {
			log.warn("/categorysdjpa, 使用者修改了類別編號，原來類別編號: " + previous_categoryId + ", 新的類別編號: " + category.getCategoryId());
			// employeeService.existsByEmployeeId(employee)需要先檢查 employee 物件是否為 
			// persistent  entity。如果是，要先移除(detach)它，因為JPA在執行 JPQL (對應 HQL)
			// 前會先進行 entityManager.flush(), 此舉會將含有重複的employeeId寫入表格中而造成錯誤
			if (categoryService.existsByCategoryId(category)) {
				log.warn("/categorysdjpa, 新的類別編號已經存在，系統不接受");	
				result.rejectValue("categoryId", "category.categoryId.exist.error", "類別編號已存在，請更換新類別編號");
				return "Category_update";
			}
		}		
		categoryService.update(category);
		ra.addFlashAttribute("message", "<font color='blue'>資料修改成功</font>");
		return "redirect:/category";
	}
	
	@ModelAttribute
	public Category getMember(@PathVariable Integer id, Model model) {
		Category category = null;
		Optional<Category> optional = categoryService.findById(id);
		if (optional.isPresent()) {
			category = optional.get();
			model.addAttribute("previous_category_id", category.getCategoryId());
		} else {
			throw new RuntimeException("查無此筆紀錄: 鍵值: " + id);
		} 
		log.info("/categorysdjpa, 依照傳入的主鍵值:" + id + "讀取對應的紀錄");
		return category;
	}		
}
