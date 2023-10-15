package com.ispan.eeit69.controller.orderpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
	@GetMapping("/cart")
	public String hello() {
		return "cart"; // 這個方法會處理路徑為 "/hello" 的GET請求
	}
}
