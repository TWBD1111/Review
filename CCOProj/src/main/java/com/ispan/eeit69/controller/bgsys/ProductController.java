package com.ispan.eeit69.controller.bgsys;
	
import java.io.File;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.ispan.eeit69.Validator.ProductValidator;
import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.model.Product;
import com.ispan.eeit69.service.CategoryService;
import com.ispan.eeit69.service.LabelService;
import com.ispan.eeit69.service.ProductService;
import com.ispan.eeit69.utils.SystemService;

@Controller
public class ProductController extends AbstractController{
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	ProductService productService;
	// 檢查資料是否正確的驗證器(Insert+update)
	ProductValidator productValidator;

    CategoryService categoryService;    
	LabelService labelService;
		
	public ProductController(ProductService productService, ProductValidator productValidator,
			CategoryService categoryService, LabelService labelService) {
		super();
		this.productService = productService;
		this.productValidator = productValidator;
		this.categoryService = categoryService;
		this.labelService = labelService;
	}	
	// 	查詢所有的商品資料
	@GetMapping("/product")
	public String findAll(@RequestParam(name = "page", defaultValue = "0") int page,Model model) {
		List<Product>  products = productService.findAll();
		model.addAttribute(products);   // 使用預設的識別字串 "employeeList"
	    int pageSize = 8; // 每頁顯示的商品數量
	    Page<Product> productPage = productService.findAll(PageRequest.of(page, pageSize));
	    List<Product> nonEmptyProducts = productService.getNonEmptyProducts(products);
	    model.addAttribute("productPage", productPage);
	    model.addAttribute("productList", nonEmptyProducts);
		log.info("送出所有商品資料供Product_all.jsp顯示");
		return "Product_all";
	}	
    // 搜尋商品
	@GetMapping("/searchProducts")
	public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
		log.info("Searching for keyword: " + keyword);
	    List<Product> searchResults = productService.searchProducts(keyword);
	    model.addAttribute("searchResults", searchResults);
	    return "SearchResults";  // Assuming this is your main JSP file
	}
	// 新增商品資料
    // 當使用者在首頁按下『新增員工資料』超連結十，由本方法送出可讓使用者新增員工資料的空白表單。
    // 由Product_insert.jsp顯示空白表單，以便使用者於瀏覽器上新員工增資料。
	@GetMapping("/product/insertProduct")
	public String sendEmptyData(Model model) {
		Product product = new Product();
        List<Category> categories = categoryService.findAll();
        List<Label> labels = labelService.findAll();
		model.addAttribute(product);
        model.addAttribute(categories);
        model.addAttribute(labels);
		log.info("/productsdjpa, 送出可供前端使用者新增的空白表單");
		return "Product_insert";
	}
	// 當使用者在『新建員工資料』表單新增資料完畢，按下Submit按鈕後資料會送達本方法，
	// 本方法將進行驗證與表格資料的新增。
	// 要點如下:
		// 1. public String saveEmployee(@ModelAttribute Employee employee, .....)
		//    使用位於Model內的屬性物件來接收前端送來的員工資料，Model內的屬性物件是由本類別中
		//    由@ModelAttribute修飾之方法public Employee beforeSave() 先建立用來儲存前端送來資源工資料。
	    //    識別字串未標明，乃採預設值"employee"。
		// 2. BindingResult存放與資料綁定有關的錯誤訊息
		// 3. 由於表單含有日期(其他如數值資料亦同)，會因為資料格式不正確而無法進行綁定
		//    (即無法將資料放入Employee物件內)而發生錯誤，要若能顯示相關的錯誤訊息，
		//    需要建立 /src/main/resources/messages.properties 檔案，並在其內自行定義相關的錯誤訊息。
		//    訊息檔預設的 base name 為messages，否則需要於 application.properties內定義 base name:
	    //    spring.messages.basename=your_messages_file_base_name    
	
	@PostMapping("/product/insertProduct")
	public String saveProduct(@ModelAttribute Product product,
			@RequestParam("category") String category,
			BindingResult result, 
			Model model, RedirectAttributes ra) throws SerialException, SQLException {

		productValidator.validate(product, result);
		        
		if (result.hasErrors()) {
			log.warn("/productsdjpa, 前端使用者送回的表單含有錯誤資料");
			log.warn("/productsdjpa, -------------------------------------");
			// -------------------------------------
			// 下列四列敘述只有需要獲知由於格式錯而無法綁定時所需的錯誤訊息的Key才會用到它們
			List<ObjectError> errors = result.getAllErrors();
	        List<Category> categories = categoryService.findAll();
	        List<Label> labels = labelService.findAll();
	        model.addAttribute(categories);
	        model.addAttribute(labels);

			for(ObjectError error : errors) {
				log.warn("/productsdjpa, " + error.toString());
			}
			log.warn("/productsdjpa, -------------------------------------");
			// -------------------------------------
			// 如果使用者挑選圖片，if敘述的條件會是true
			if (!result.hasFieldErrors("fileName")) {
				// 將圖片與圖片檔名的取出存入Model內以方便於驗證失敗時導回原輸入畫面時仍能顯示原來的圖片
				model.addAttribute("image", product.getImage());
				model.addAttribute("fileName", product.getFileName());
			} 
			return "Product_insert";
		}
		if (productService.existsByProductId(product)) {
			log.warn("/productsdjpa, 提供的商品編號已存在: " + product.getProductId());
			result.rejectValue("productId", "product.productId.exist.error", "商品編號已存在，請更換新的商品編號");
	        List<Category> categories = categoryService.findAll();
	        List<Label> labels = labelService.findAll();
	        model.addAttribute(categories);
	        model.addAttribute(labels);
			model.addAttribute("image", product.getImage());
			model.addAttribute("fileName", product.getFileName());
			return "Product_insert";
		}
		char[] c = product.getImage().toCharArray();
		Clob clob = new SerialClob(c);
		product.setPicture(clob);
		productService.save(product);
		// 要將圖檔寫入Server端的資料夾		
		File imageMainFolder = new File(SystemService.PRODUCT_IMAGE_FILE_FOLDER);
		String fileExt = ".txt";
		File outFile = new File(imageMainFolder, "Product_" + product.getProductId() + fileExt);
		try (
			PrintWriter pw = new PrintWriter(outFile);
		){
			pw.print(product.getImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ra.addFlashAttribute("message", "<font color='blue'>資料新增成功</font>");
		// 新增與修改成功一定要用 redirect: 傳回新增/修改成功的訊息
		log.info("/productsdjpa, 新增商品資料已經完成");
		return "redirect:/product";
	}	
	@ModelAttribute	
	public Product beforeSave() {
		Product product = new Product();
		// 於新增員工資料前加入系統產生的資料
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		product.setCreated_at(ts);
		return product;
	}
	
	// 使用@DeleteMapping來處理 HTTP DELETE請求
    // 1. RedirectAttributes的 addFlashAttribute()方法可以在不同請求中傳遞訊息，
    //    此訊息一經顯示就會由 HttpSession自動移除
    // 2. 為了符合REST風格，欲刪除之紀錄的主鍵由PathVariable來傳遞
    // 3. 若由其他訊息要傳送可以使用請求參數(Request Parameter)來傳送 
	@DeleteMapping("/product/ProductDelete/{id}")
	public String deleteProduct(RedirectAttributes ra, 
			@PathVariable Integer id,
			@RequestParam String empNo
			) {
		try {
			productService.deleteById(id);
			ra.addFlashAttribute("message", "<font color='green'>已刪除商品編號: " +  empNo + " 之紀錄</font>");
			log.info("/productsdjpa, 已刪除商品編號: " +  empNo + " 之紀錄");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "<font color='red'>刪除商品編號: " +  empNo + " 之紀錄失敗:" + e.getMessage() + "</font>");
			log.error("/productsdjpa, 刪除商品編號: " +  empNo + " 之紀錄失敗");
			e.printStackTrace();
		}
		return "redirect:/product";
	}
}