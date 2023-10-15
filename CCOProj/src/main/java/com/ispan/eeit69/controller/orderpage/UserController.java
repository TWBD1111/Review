package com.ispan.eeit69.controller.orderpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.model.Product;
import com.ispan.eeit69.service.ProductService;

@Controller
public class UserController {

//	@GetMapping("/main")
//	public String main() {
//		return "main";
//	}
	@RequestMapping("/opProduct")
	public String productPage() {
		return "product";
	}

	@RequestMapping("/cart")
	public String cartPage() {
		return "cart";
	}

	@Autowired
	private ProductService productService;


	@GetMapping("/main")
	public String main(Model model) {
		List<Product> productList = productService.getAllProducts();
		List<Category> categoryList = productService.getAllCategory();
		model.addAttribute("productList", productList);
		model.addAttribute("categoryList", categoryList);
		return "main";

	}
}
