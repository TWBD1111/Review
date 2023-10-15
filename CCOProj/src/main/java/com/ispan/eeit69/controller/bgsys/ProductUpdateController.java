package com.ispan.eeit69.controller.bgsys;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialClob;
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

import com.ispan.eeit69.Validator.ProductValidator;
import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.model.Product;
import com.ispan.eeit69.service.CategoryService;
import com.ispan.eeit69.service.LabelService;
import com.ispan.eeit69.service.ProductService;

@Controller
@SessionAttributes("previous_product_id")     
//重要!!!  @SessionAttributes只能單獨一個控制器使用，确保及时清除会话中的属性，以避免数据混淆
public class ProductUpdateController extends AbstractController{
	Logger log = LoggerFactory.getLogger(ProductUpdateController.class);

	ProductService productService;
	// 檢查資料是否正確的驗證器(Insert+update)
	ProductValidator productValidator;  
    CategoryService categoryService;    
	LabelService labelService;
	
	public ProductUpdateController(ProductService productService, ProductValidator productValidator,
			CategoryService categoryService, LabelService labelService) {
		super();
		this.productService = productService;
		this.productValidator = productValidator;
		this.categoryService = categoryService;
		this.labelService = labelService;
	}
	//處理修改商品資料
	
    // 下列方法送出含有Product資料的表單以便其修改
    // 搭配本類別的@ModelAttribute方法getMember() (在最後面)讀取Product表格內的資料
    // 然後交由 Product_insert.jsp顯示，以便使用者於瀏覽器上進行修改
	@GetMapping("/product/findById/{id}")
	public String sendEmptyForm(Model model) {
		log.info("/productsdjpa, 送出可供前端使用者修改的表單");
		return "Product_update";
	}
	
	// 當使用者修改完畢，按下Submit按鈕後資料會送達後端，由本方法進行驗證與表格更新
	// 要點如下:
	// 1. public String updateEmployee(@ModelAttribute Employee employee, .....)
	//    使用位於Model內的屬性物件來接收前端送來的更新資料，Model內的屬性物件是由本類別中
	//    由@ModelAttribute修飾之方法先行讀取表格內的紀錄。識別字串未標明，乃採預設值"employee"。
	// 2. BindingResult存放與資料綁定有關的錯誤訊息
	//    它是 WebDataBinder 的子接口，主要用于存储模型属性绑定和验证的结果
	// 3. 由於表單含有日期(其他如數值資料亦同)，會因為資料格式不正確而無法進行綁定
	//    (即無法將資料放入Employee物件內)而發生錯誤，要若能顯示相關的錯誤訊息，
	//    需要建立 /src/main/resources/messages.properties 檔案，並在其內
	//    自行定義相關的錯誤訊息。
	//    訊息檔的預設 base name 為messages，否則需要於 application.properties內定義 base name:
    //    spring.messages.basename=your_messages_file_base_name
	@PutMapping("/product/editProduct/{id}")
	public String updateProduct(@ModelAttribute Product product,
			BindingResult result,
			@RequestParam String previous_productId, 
			Model model, RedirectAttributes ra) throws SerialException, SQLException {
		// 對使用者輸入之資料進行驗證
		productValidator.validate(product, result);
		// 如果修改後的資料有錯誤
		if (result.hasErrors()) {    
			log.warn("/productsdjpa, 前端使用者送回的表單含有錯誤資料");
			log.warn("/productsdjpa, -------------------------------------");
			// -------------------------------------
			// 下列迴圈僅為了找出顯示我們需要之錯誤訊息所需的key: 
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				log.warn("/productsdjpa, " + error.toString());
			}
			log.warn("/productsdjpa, -------------------------------------");
			// -------------------------------------
			if (!result.hasFieldErrors("fileName")) {   
				// 為了能正確顯示圖片
				model.addAttribute("image", product.getImage());
				model.addAttribute("fileName", product.getFileName());
			} 
			// 導向原來修改資料的頁面Product_update.jsp
			// 重要，重要，重要: Product_update.jsp 的<%@ page ... %>一定加上  isErrorPage='true'
			return "Product_update";
		}
		// 如果修改員工編號，需要檢查修改後的員工編號是否已經存在
		if  (!previous_productId.equals(product.getProductId()) ) {
			log.warn("/productsdjpa, 使用者修改了商品編號，原來商品編號: " + previous_productId + ", 新的商品編號: " + product.getProductId());
			// employeeService.existsByEmployeeId(employee)需要先檢查 employee 物件是否為 
			// persistent  entity。如果是，要先移除(detach)它，因為JPA在執行 JPQL (對應 HQL)
			// 前會先進行 entityManager.flush(), 此舉會將含有重複的employeeId寫入表格中而造成錯誤
			if (productService.existsByProductId(product)) {
				log.warn("/productsdjpa, 新的商品編號已經存在，系統不接受");	
				result.rejectValue("productId", "product.productId.exist.error", "商品編號已存在，請更換新的商品編號");
				model.addAttribute("image", product.getImage());
				model.addAttribute("fileName", product.getFileName());
				// 
				return "Product_update";
			}
		}
		// 將圖片資料(data url)轉換為 Clob
		char[] c = product.getImage().toCharArray();
		Clob clob = new SerialClob(c);
		product.setPicture(clob);
		
		productService.update(product);
		ra.addFlashAttribute("message", "<font color='blue'>資料修改成功</font>");
		// 新增與修改成功一定要用 redirect: 傳回新增/修改成功的訊息
		log.info("/productsdjpa, 修改員工資料已經完成");
		return "redirect:/product";
	}
	
	@ModelAttribute
	public Product getMember(@PathVariable Integer id, Model model) {
		Product product = null;
		Optional<Product> optional = productService.findById(id);
		if (optional.isPresent()) {
			product = optional.get();
			model.addAttribute("previous_product_id", product.getProductId());
			
	        // Get category and labels for the product
	        List<Category> allcategories = categoryService.findAll();
	        List<Label> alllabels = labelService.findAll();

	        model.addAttribute("allcategories", allcategories);
	        model.addAttribute("alllabels", alllabels);	
	        
	        model.addAttribute("selectedCategory", product.getCategory());
	        model.addAttribute("selectedLabels", product.getLabels());
		} else {
			throw new RuntimeException("查無此筆紀錄: 鍵值: " + id);
		} 
		log.info("/productsdjpa, 依照傳入的主鍵值:" + id + "讀取對應的紀錄");
		return product;
	}	
}
