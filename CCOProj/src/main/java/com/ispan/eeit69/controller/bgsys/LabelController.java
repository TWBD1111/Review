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

import com.ispan.eeit69.Validator.LabelValidator;
import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.service.LabelService;

@Controller
public class LabelController extends AbstractController{
	Logger log = LoggerFactory.getLogger(LabelController.class);
	
	LabelService labelService;
	LabelValidator labelValidator;
	
//	private List<Label> labelList = new ArrayList<>();

	public LabelController(LabelService labelService,LabelValidator labelValidator) {
		this.labelService = labelService;
		this.labelValidator = labelValidator;
	}
	
    @GetMapping("/label")
	public String findAll(Model model) {
		List<Label>  Labels = labelService.findAll();
		model.addAttribute(Labels);
		return "Label_all";
	}
	
    @GetMapping("/label/insertLabel")
	public String sendEmptyData(Model model) {
		Label label = new Label();
		model.addAttribute(label);
		return "Label_insert";
	}
	
    @PostMapping("/label/insertLabel")
	public String saveLabel(@ModelAttribute Label label, BindingResult result, 
			Model model, RedirectAttributes ra) throws SerialException, SQLException {

		labelValidator.validate(label, result);
		if (result.hasErrors()) {
			log.warn("/labelsdjpa, 前端使用者送回的表單含有錯誤資料");
			log.warn("/labelsdjpa, -------------------------------------");
			// -------------------------------------
			// 下列四列敘述只有需要獲知由於格式錯而無法綁定時所需的錯誤訊息的Key才會用到它們
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				log.warn("/labelsdjpa, " + error.toString());
			}
			log.warn("/labelsdjpa, -------------------------------------");
			return "Label_insert";
		}
//		if (labelService.existsByLabelId(label)) {
//			log.warn("/categorysdjpa, 提供的標籤編號已存在: " + label.getLabelId());
//			result.rejectValue("labelId", "label.labelId.exist.error", "標籤編號已存在，請更換新的標籤編號");
//			return "Label_insert";
//		}	
		labelService.save(label);
		
		ra.addFlashAttribute("message", "<font color='blue'>資料新增成功</font>");
		return "redirect:/label";
	}
    
	@DeleteMapping("/label/LabelDelete/{id}")
	public String deleteLabel(RedirectAttributes ra, 
			@PathVariable Integer id,
			@RequestParam String empNo
			) {
		try {
			labelService.deleteById(id);
			ra.addFlashAttribute("message", "<font color='green'>已刪除標籤編號: " +  empNo + " 之紀錄</font>");
			log.info("/productsdjpa, 已刪除標籤編號: " +  empNo + " 之紀錄");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "<font color='red'>刪除標籤編號: " +  empNo + " 之紀錄失敗:" + e.getMessage() + "</font>");
			log.error("/productsdjpa, 刪除標籤編號: " +  empNo + " 之紀錄失敗");
			e.printStackTrace();
		}
		return "redirect:/label";
	}
//	@RequestMapping("/label/insertLabel")
//	public void processData(@RequestParam("labels") String[] labelValues) {
//	    List<Integer> selectedLabelIds = new ArrayList<>();
//	    for (String labelValue : labelValues) {
//	        if (!"none".equals(labelValue)) { // 檢查是否為 "none"
//	            try {
//	                Integer labelId = Integer.parseInt(labelValue);
//	                selectedLabelIds.add(labelId);
//	            } catch (NumberFormatException e) {
//	                // 處理轉換失敗的情況
//	            }
//	        }
//	    }
//	}	
}

