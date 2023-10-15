package com.ispan.eeit69.controller.website;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/index.html")
	public String toJumpPageIndex() {
		System.out.println("跳转页面index");
		return "index";
	}
	
	@GetMapping("/index")
	public String toJumpPageIndexJSP() {
		System.out.println("跳转页面index");
		return "index";
	}
	
	@GetMapping("/EnterInvitationCode")
	public String toJumpPageEnterInvitationCode() {
		System.out.println("跳转EnterInvitationCode页面");//所有商品列表
		return "EnterInvitationCode";
	}

	@GetMapping("/login.html")
	public String toJumpPageLogin() {
		System.out.println("跳转页面login");
		return "login";
	}
	
	@GetMapping("/loginOk")
	public String toJumpPageLoginOk() {
		System.out.println("跳转页面login");
		return "loginOk";
	}

	@GetMapping("/contact.html")
	public String toJumpPageContact() {
		System.out.println("跳转页面contact");
		return "contact";
	}

	@GetMapping("/OderSystem.html")
	public String toJumpPageOderSystem() {
		System.out.println("跳转页面OderSystem");
		return "OderSystem";
	}

	@GetMapping("/plan.html")
	public String toJumpPagePlan() {
		System.out.println("跳转页面");
		return "plan";
	}

	@GetMapping("/Report.html")
	public String toJumpPageReport() {
		System.out.println("跳转页面");
		return "Report";
	}

	@GetMapping("/register")
	public String toJumpPageregister() {
		System.out.println("跳转页面");
		return "register";
	}
	
	@GetMapping("/registerOk")
	public String toJumpPageregisterOk() {
		System.out.println("跳转registerOk页面");
		    return "registerOk";
	}
	
	@GetMapping("/BusinessInformation")
	public String toJumpPageBusinessInformation(HttpSession session) {
		System.out.println("跳转BusinessInformation页面");
		Object levelObject = session.getAttribute("level");
		if (levelObject != null) {
			String level = levelObject.toString();
			if ("A".equals(level)) {
				return "BusinessInformation";
			}
		}
		return "login";
	}
	
	@GetMapping("/Product_all")
	public String toJumpPageProduct_all() {
		System.out.println("跳转Product_all页面");//所有商品列表
		return "Product_all";
	}
	
	@GetMapping("/Category_all")
	public String toJumpPageCategory_all() {
		System.out.println("跳转Category_all页面");//商品類別列表
		return "Category_all";
	}
	
	@GetMapping("/Label_all")
	public String toJumpPageLabel_all() {
		System.out.println("跳转Label_all页面");//商品客製標籤列表
		return "Label_all";
	}
	
//	@GetMapping("/showOrderSystem")
//	public String toJumpPageshowOrderSystem() {
//		System.out.println("跳转showOrderSystem页面");//接單系統
//		return "showOrderSystem";
//	}
//	
//	@GetMapping("/kitchenDisplaySystem")
//	public String toJumpPagekitchenDisplaySystem() {
//		System.out.println("跳转kitchenDisplaySystem页面");//廚房看版系統
//		return "kitchenDisplaySystem";
//	}
//	
//	@GetMapping("/showcompletedsystem")
//	public String toJumpPageshowcompletedsystem() {
//		System.out.println("跳转showcompletedsystem页面");//歷史訂單查詢
//		return "showcompletedsystem";
//	}
	
//	@GetMapping("/chart")
//	public String toJumpPagechart() {
//		System.out.println("跳转charte页面");//報表分析系統
//		return "chart";
//	}
//	
//	@GetMapping("/board")
//	public String toJumpPageboard() {
//		System.out.println("跳转/board页面");//系統公告
//		return "board";
//	}
	
	@GetMapping("/Category_insert")
	public String toJumpPageCategory_insert() {
		System.out.println("跳转Category_insert页面");//新增類別資料
		return "Category_insert";
	}
	
	@GetMapping("/Category_update")
	public String toJumpPageCategory_update() {
		System.out.println("跳转Category_update页面");//修改類別資料
		return "Category_update";
	}
	
	@GetMapping("/finale")
	public String toJumpPagefinal() {
		System.out.println("跳转final页面");//訂單完成
		return "final";
	}
	
	@GetMapping("/Label_insert")
	public String toJumpPageLabel_inserte() {
		System.out.println("跳转Label_insert页面");//新增客製標籤
		return "Label_insert";
	}
	
	@GetMapping("/Label_update")
	public String toJumpPageLabel_update() {
		System.out.println("跳转Label_update页面");//修改標籤資料
		return "Label_update";
	}
	
	@GetMapping("/Product_insert")
	public String toJumpPageProduct_insert() {
		System.out.println("跳转Product_insert页面");//新增客製標籤
		return "Product_insert";
	}
	
	@GetMapping("/Product_update")
	public String toJumpPageProduct_update() {
		System.out.println("跳转Product_update页面");//修改客制標籤資料
		return "Product_update";
	}
	
	
	@GetMapping("/productIndex")
	public String toJumpPageproductIndex() {
		System.out.println("跳转productIndex页面");//商品管理總覽
		return "productIndex";
	}
	
	@GetMapping("/SearchResults")
	public String toJumpPageSearchResults() {
		System.out.println("跳转SearchResults页面");//搜尋結果
		return "SearchResults";
	}
	
//	@GetMapping("/main")
//	public String toJumpPagemain() {
//		System.out.println("跳转main页面");//CCO早餐店
//		return "main";
//	}
	
//	@GetMapping("/product")
//	public String toJumpPageproduct() {
//		System.out.println("跳转product页面");//商品詳細頁
//		return "product";
//	}
//	
//	@GetMapping("/cart")
//	public String toJumpPagecart() {
//		System.out.println("跳转cart页面");//購物車
//		return "cart";
//	}
	
//	@GetMapping("/thanks")
//	public String toJumpPagethanks() {
//		System.out.println("跳转thanks页面");//感謝填寫！
//		return "thanks";
//	}
}