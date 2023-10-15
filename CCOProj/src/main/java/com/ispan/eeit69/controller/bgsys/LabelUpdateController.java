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

import com.ispan.eeit69.Validator.LabelValidator;
import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.service.LabelService;

@Controller
@SessionAttributes("previous_label_id")  
public class LabelUpdateController extends AbstractController{
	Logger log = LoggerFactory.getLogger(LabelUpdateController.class);
	
	LabelService labelService;
	// 檢查資料是否正確的驗證器(Insert+update)
	LabelValidator labelValidator;
	
	public LabelUpdateController(LabelService labelService, LabelValidator labelValidator) {
		super();
		this.labelService = labelService;
		this.labelValidator = labelValidator;
	}
	@GetMapping("/label/findById/{id}")
	public String sendEmptyForm(Model mode) {
		return "Label_update";
	}
	
	@PutMapping("/label/editLabel/{id}")
	public String updateLabel(@ModelAttribute Label label,
			BindingResult result,
			@RequestParam String previous_labelId, 
			Model model, RedirectAttributes ra) throws SerialException, SQLException {
		// 對使用者輸入之資料進行驗證
		labelValidator.validate(label, result);
		// 如果修改後的資料有錯誤
		if (result.hasErrors()) {    
			log.warn("/labelsdjpa, 前端使用者送回的表單含有錯誤資料");
			log.warn("/labelsdjpa, -------------------------------------");
			// -------------------------------------
			// 下列迴圈僅為了找出顯示我們需要之錯誤訊息所需的key: 
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				log.warn("/labelsdjpa, " + error.toString());
			}
			log.warn("/labelsdjpa, -------------------------------------");
			return "Label_update";
		}
		// 如果修改員工編號，需要檢查修改後的員工編號是否已經存在
//		if  (!previous_labelId.equals(label.getLabelId()) ) {
//			log.warn("/labelsdjpa, 使用者修改了標籤編號，原來標籤編號: " + previous_labelId + ", 新的標籤編號: " + label.getLabelId());
//			// employeeService.existsByEmployeeId(employee)需要先檢查 employee 物件是否為 
//			// persistent  entity。如果是，要先移除(detach)它，因為JPA在執行 JPQL (對應 HQL)
//			// 前會先進行 entityManager.flush(), 此舉會將含有重複的employeeId寫入表格中而造成錯誤
//			if (labelService.existsByLabelId(label)) {
//				log.warn("/labelsdjpa, 新的標籤編號已經存在，系統不接受");	
//				result.rejectValue("labelId", "label.labelId.exist.error", "標籤編號已存在，請更換新標籤編號");
//				return "Label_update";
//			}
//		}		
		labelService.update(label);
		ra.addFlashAttribute("message", "<font color='blue'>資料修改成功</font>");
		return "redirect:/label";
	}
	
	@ModelAttribute
	public Label getMember(@PathVariable Integer id, Model model) {
		Label label = null;
		Optional<Label> optional = labelService.findById(id);
		if (optional.isPresent()) {
			label = optional.get();
			model.addAttribute("previous_label_id", label.getLabelId());
			label.setLabelPrice(label.getLabelPrice());
		} else {
			throw new RuntimeException("查無此筆紀錄: 鍵值: " + id);
		} 
		log.info("/labelsdjpa, 依照傳入的主鍵值:" + id + "讀取對應的紀錄");
		return label;
	}		
}
